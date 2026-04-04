package org.example.Repository;
import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.jooq.Result;
import org.jooq.impl.DSL;
import java.sql.*;
import java.time.LocalDate;
import org.jooq.DSLContext;
import javax.sql.DataSource;
import static org.example.jooq.generated.tables.Problems.PROBLEMS;
import static org.example.jooq.generated.tables.RecallDb.RECALL_DB;
import org.example.jooq.generated.tables.records.ProblemsRecord;
import org.example.jooq.generated.tables.records.RecallDbRecord;

public class ProblemRepository {
    private final DataSource dataSource; // Store Datasource not connections

    public ProblemRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addProblem(Problem problem) {
        try {
            Connection connection = dataSource.getConnection();
            DSLContext context = DSL.using(connection); // Entry point Object to talk to databases through JOOQ interfaces
            context.insertInto(DSL.table("RECALL_DB"))
                    .columns(
                            DSL.field("ID"),
                            DSL.field("TITLE"),
                            DSL.field("TOPICS"),
                            DSL.field("STATUS"),
                            DSL.field("DATEADDED"),
                            DSL.field("STEP"),
                            DSL.field("NEXTRECALL"),
                            DSL.field("DIFFICULTY")
                    )
                    .values(
                            problem.getId(),
                            problem.getTitle(),
                            problem.getTopics(),
                            problem.getResult(),
                            problem.getDateAdded(),
                            problem.getStep(),
                            problem.getNextRecall(), // pattern
                            problem.getDifficulty())
                    .execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LeetcodeProblem getProblem(int problemID)  {
        try {
            Connection connection = dataSource.getConnection();
            DSLContext create = DSL.using(connection);

            ProblemsRecord problemRecord = create
                    .selectFrom(PROBLEMS)
                    .where(PROBLEMS.PROBLEM_ID.eq(String.valueOf(problemID)))
                    .fetchOne();

            int problemId = Integer.parseInt(problemRecord.getProblemId());
            String problem_slug = problemRecord.getProblemSlug();
            String difficulty = problemRecord.getDifficulty();
            String topics = String.valueOf(problemRecord.getTopics());
            connection.close();
            return new LeetcodeProblem(problemId, problem_slug, difficulty, topics);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void fetchDueProblems() {
        // returns list of problem for specific date (used by today service)
        try {

            LocalDate today = LocalDate.now();
            Connection connection = dataSource.getConnection();
            DSLContext create = DSL.using(connection);
            Result<RecallDbRecord> result = create
                    .selectFrom(RECALL_DB)
                    .where(RECALL_DB.NEXTRECALL.eq(today))
                    .and(RECALL_DB.NEXTRECALL.greaterOrEqual(today))
                    .fetch();
            System.out.println("RecallDBRecord " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Problem fetchRecallProblem(int problemID) {
        try {
            Connection connection = dataSource.getConnection();
            DSLContext create = DSL.using(connection);

            RecallDbRecord recallDbRecord = create
                    .selectFrom(RECALL_DB)
                    .where(RECALL_DB.ID.eq(problemID))
                    .fetchOne();

            int id = recallDbRecord.getId();
            String title = recallDbRecord.getTitle();
            String difficulty = recallDbRecord.getDifficulty();
            String topics = recallDbRecord.getTopics();
            //LocalDate recallDate = recallDbRecord.getNextRecall();
            return new Problem(id, title, difficulty, topics, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeProblem() {}

    public void updateProblem(Problem problem) {}
}

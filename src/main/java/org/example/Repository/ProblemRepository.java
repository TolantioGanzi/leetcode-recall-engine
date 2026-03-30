package org.example.Repository;
import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import javax.sql.DataSource;
import java.sql.*;
import static org.example.jooq.generated.tables.Problems.PROBLEMS;
import org.example.jooq.generated.tables.records.ProblemsRecord;
import org.jooq.Record;





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
                            DSL.field("RECALLDATES"),
                            DSL.field("DIFFICULTY")
                    )
                    .values(
                            problem.getId(),
                            problem.getTitle(),
                            problem.getTopics(),
                            problem.getResult(),
                            problem.getDateAdded(),
                            problem.getStep(),
                            problem.getRecallDates().toString(), // pattern
                            problem.getDifficulty())
                    .execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LeetcodeProblem getProblem(int problemNumber)  {
        try {
            Connection connection = dataSource.getConnection();
            DSLContext create = DSL.using(connection);

            ProblemsRecord problemRecord = create
                    .selectFrom(PROBLEMS)
                    .where(PROBLEMS.PROBLEM_ID.eq(String.valueOf(problemNumber)))
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

    public void getProblemsByDate() {
        
    }
    public void removeProblem() {
    }

    public void updateProblem(Problem problem) {
    }
}

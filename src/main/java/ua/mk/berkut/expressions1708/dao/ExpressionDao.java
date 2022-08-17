package ua.mk.berkut.expressions1708.dao;

import lombok.AllArgsConstructor;
import ua.mk.berkut.expressions1708.tables.MyExpression;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ExpressionDao {
    private DataSource ds;

    public void saveToDB(String expression, double result) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into expression (expr, result) VALUES (?, ?)")
        ) {
            ps.setString(1, expression);
            ps.setDouble(2, result);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MyExpression> findAll() {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expression")
        ) {
            List<MyExpression> expressions = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String expr = rs.getString("expr");
                double result = rs.getDouble("result");
                expressions.add(new MyExpression(id, expr, result));
            }
            rs.close();
            return expressions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from expression where id = ?")
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<MyExpression> findById(int id) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expression where id = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//                int id1 = rs.getInt("id");
                String expr = rs.getString("expr");
                double result = rs.getDouble("result");
                return Optional.of(new MyExpression(id, expr, result));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateById(int id, String expr, double v) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("update expression set expr = ?, result = ? where id = ?")
        ) {
            ps.setInt(3, id);
            ps.setString(1, expr);
            ps.setDouble(2, v);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

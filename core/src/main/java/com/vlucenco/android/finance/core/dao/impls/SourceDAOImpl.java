package com.vlucenco.android.finance.core.dao.impls;

import com.vlucenco.android.finance.core.dao.interfaces.SourceDAO;
import com.vlucenco.android.finance.core.database.SQLiteConnection;
import com.vlucenco.android.finance.core.enums.OperationType;
import com.vlucenco.android.finance.core.impls.DefaultSource;
import com.vlucenco.android.finance.core.interfaces.Source;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO можно реализовать общий абстрактный класс и вынести туда общие методы (getAll, delete и пр.)
// реализация DAO не должна заниматься лишними делами - только связь с БД, заполнение объектов
public class SourceDAOImpl implements SourceDAO {

    private static final String SOURCE_TABLE = "source";
    private List<Source> sourceList = new ArrayList<>();// хранит все элементы сплошным списком, без разделения по деревьям и пр.

    @Override
    public List<Source> getAll() {
        sourceList.clear();

        try (Statement stmt = SQLiteConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + SOURCE_TABLE+" order by parent_id");) {

            while (rs.next()) {
                DefaultSource source = new DefaultSource();
                source.setId(rs.getLong("id"));
                source.setName(rs.getString("name"));
                source.setParentId(rs.getLong("parent_id"));
                source.setOperationType(OperationType.getType(rs.getInt("operation_type_id")));
                sourceList.add(source);
            }

            return sourceList;// должен содержать только корневые элементы

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    @Override
    public Source get(long id) {
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("select * from " + SOURCE_TABLE + " where id=?");) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery();){
                DefaultSource source = null;

                if (rs.next()){
                    source = new DefaultSource();
                    source.setId(rs.getLong("id"));
                    source.setName(rs.getString("name"));
                    source.setParentId(rs.getLong("parent_id"));
                    source.setOperationType(OperationType.getType(rs.getInt("operation_type_id")));
                }

                return source;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    @Override
    public boolean update(Source source) {
        // для упрощения - у хранилища даем изменить только название, изменять parent_id нельзя (для этого можно удалить и заново создать)
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("update " + SOURCE_TABLE + " set name=? where id=?");) {

            stmt.setString(1, source.getName());// у созданного элемента - разрешаем менять только название
            stmt.setLong(2, source.getId());

            // не даем обновлять operationType - тип устанавливается только один раз при создании корневеого элемента

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public boolean delete(Source source) {
        // TODO реализовать - если есть ли операции по данному хранилищу - запрещать удаление
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("delete from " + SOURCE_TABLE + " where id=?");) {

            stmt.setLong(1, source.getId());

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public List<Source> getList(OperationType operationType) {
        sourceList.clear();

        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("select * from " + SOURCE_TABLE + " where operation_type_id=?");) {

            stmt.setLong(1, operationType.getId());

            try (ResultSet rs = stmt.executeQuery();){
                DefaultSource source = null;

                while (rs.next()){
                    source = new DefaultSource();
                    source.setId(rs.getLong("id"));
                    source.setName(rs.getString("name"));
                    source.setParentId(rs.getLong("parent_id"));
                    source.setOperationType(OperationType.getType(rs.getInt("operation_type_id")));
                    sourceList.add(source);
                }

                return sourceList;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }
}

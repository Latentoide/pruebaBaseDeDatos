package es.ieslavereda.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Model{
    public List<Person> getPersons(){
        List<Person> personList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyOracleDataSource();


        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from person")){
            String dni, nombre, apellidos;
            int edad;
            while(resultSet.next()){
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                edad = resultSet.getInt("edad");
                Person p = new Person(dni, nombre, apellidos, edad);
                personList.add(p);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return personList;
    }

    public List<Empleado> getEmpleados(){
        List<Empleado> empleadoList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from empleado")){
            String dni, nombre, apellidos;
            while(resultSet.next()){
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                Empleado empleado = new Empleado(dni, nombre, apellidos);
                empleadoList.add(empleado);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return empleadoList;
    }
}

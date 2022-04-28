package es.ieslavereda.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static oracle.security.pki.util.SignatureAlgorithms.e;

public class Model{
    public List<Person> getPersons(){
        List<Person> personList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMySQLDataSource();


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
            String dni, nombre, apellidos, password;
            while(resultSet.next()){
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                password = resultSet.getString("password");
                Empleado empleado = new Empleado(dni, nombre, apellidos, password);
                empleadoList.add(empleado);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return empleadoList;
    }

    public boolean authenticate(String email, String password){
        DataSource dataSource = MyDataSource.getMyOracleDataSource();
        int count = 0;
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from empleado where email like '" + email + "' and ENCRYPT_PASWD.decrypt_val(password) like '" + password + "'")){
            resultSet.next();
            count = resultSet.getInt(1);

        }catch (SQLException e){
            e.printStackTrace();
        }

        if(count == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void addPerson(Person person){

        DataSource ds = MyDataSource.getMySQLDataSource();

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();){
                String sql = "insert into person values ('" + person.getDni() + "','" + person.getNombre() +"','" + person.getApellidos()+ "', " + person.getEdad()+ ")";
                int count = statement.executeUpdate(sql);
                System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deletePerson(Person person){

        DataSource ds = MyDataSource.getMySQLDataSource();
        String sql = "delete from person where dni like ?";
        try(Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){

            int pos = 0;
            preparedStatement.setString(++pos, person.getDni());

            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person){
        DataSource ds = MyDataSource.getMySQLDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();
        ) {
            String sql = "update person set nombre = '" + person.getNombre() + "', apellidos = '" + person.getApellidos() + "', edad = " + person.getEdad()  + " where dni = '" + person.getDni() +"'";
            int count = statement.executeUpdate(sql);
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

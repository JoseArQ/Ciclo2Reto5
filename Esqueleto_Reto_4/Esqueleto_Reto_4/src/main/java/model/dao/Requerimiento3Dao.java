package model.dao;

//Estructura de datos
import java.util.ArrayList;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import util.JDBCUtilities;

//Encapsulamiento de los datos
import model.vo.Requerimiento3;

public class Requerimiento3Dao {

   
    public ArrayList<Requerimiento3> rankingMaterialesComprasDescendente() throws SQLException {

        ArrayList<Requerimiento3> respuesta = new ArrayList<Requerimiento3>();
        Connection conexion = JDBCUtilities.getConnection();

        try {

            String consulta = "SELECT m.Nombre_Material, m.Importado, COUNT(*) No_Compras "
                    + "FROM MaterialConstruccion m INNER JOIN Compra c "
                    + "ON m.ID_MaterialConstruccion = c.ID_MaterialConstruccion "
                    + "WHERE m.Importado = 'Si'  "
                    + "GROUP BY c.ID_MaterialConstruccion\n"
                    + "ORDER BY No_Compras ASC;";//Consulta SQL

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            //Recorrer los registros en los VO específicos
            while (resultSet.next()) {
                Requerimiento3 materialRankeadoCompras = new Requerimiento3();
                materialRankeadoCompras.setNombreMaterial(resultSet.getString("Nombre_Material"));
                materialRankeadoCompras.setImportado(resultSet.getString("Importado"));
                //Codigo
                materialRankeadoCompras.setNoCompras(resultSet.getInt("No_Compras"));
                //Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(materialRankeadoCompras);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error consultando ranking de materiales importados según número de compras: " + e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }

        //Retornar la colección de vo's
        return respuesta;

    }

}

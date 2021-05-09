package ui;

import java.util.ArrayList;

/**
 * Los imports de la libreria externa para mostrar los grafos de manera visual
 */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ControllerGUI {
	
	 @FXML
	    private BorderPane BorderPane1;

	    @FXML
	    private TextArea VariableTA;

	    @FXML
	    private TextArea CadenaTA;

	    @FXML
	    private Button GenerarMatrizBT;

	    @FXML
	    private Button ProbarCadena;

	    @FXML
	    private ScrollPane ScrollPane1;

	    public static int columns;
	    
	    public static int rows;
	    
	    public static TextField [][] TransformationMatrix;
	    public static GridPane InputGrid;
	    
	    
	    /**
	     * Inicializa los objetos y metodos necesarios para el controlador
	     */
	    @FXML
	    public void initialize() {
	  
	       
	    }

	    /**
	     * Genera la matriz de entrada para que el usuario ingrese los datos del automata
	     * Dependiendo de la opcion del Moore o MEaley del combobox
	     */
	    @FXML
	    public void generate() {
	    	//Se recolectan los datos de los TextAreas y se colocan de manera legible para operaciones
	    	//en el GridPane
	    	
	    rows = Integer.parseInt(VariableTA.getText());
	   
		 columns = 2;
	  
	        if (rows <= 26) {
	        	  InputGrid = new GridPane();
	            
	            ScrollPane1.setContent(InputGrid);

	            try {
	         TransformationMatrix = new TextField[columns + 1][rows];
	          
	          //Se Colocan los indices superiores en el GridPane de Entrada

	          
	              TextField HeaderTextField = new TextField("Variable");
	              HeaderTextField.setEditable(false);
	              HeaderTextField.setPrefWidth(80);
	              InputGrid.add(HeaderTextField, 0, 0);
	          

	              HeaderTextField = new TextField("---->");
	              HeaderTextField.setEditable(false);
	              HeaderTextField.setPrefWidth(80);
	              HeaderTextField.setEditable(false);;
	              InputGrid.add(HeaderTextField, 1, 0);
	              
	              
	              HeaderTextField = new TextField("produciones");
	              HeaderTextField.setEditable(false);
	              HeaderTextField.setPrefWidth(80);
	              InputGrid.add(HeaderTextField, 2, 0);
	          
	          //Se colocan todas las Textfields en el GridPane dependiendo del tamano de las entradas
	               for (int i = 1; i < rows + 1; i++) {
	               TextField CoordinateTextField = new TextField();
	                CoordinateTextField.setEditable(false);
	                CoordinateTextField.setPrefWidth(80);
	                    
	                  InputGrid.add(CoordinateTextField, 0, i);
	                  
	                  
	                           for (int j = 1; j < columns + 1; j++) {
	                      TextField CoordinateTextField1= new TextField();
	                      CoordinateTextField1.setPrefWidth(80);
	                      CoordinateTextField1.setText("");;
	                      
	                               TransformationMatrix[j - 1][i - 1] = CoordinateTextField1;
	                                 InputGrid.add(CoordinateTextField1, j, i);
	                    }
	                           
	                   
	                }
	            } catch (NegativeArraySizeException | IllegalArgumentException e) {
	                Alert a = new Alert(Alert.AlertType.ERROR);
	                a.setContentText(e.getMessage());
	                a.show();
	            }
	        } else {
	            Alert a = new Alert(Alert.AlertType.ERROR);
	            a.setContentText("Although the model lets you manage many states, the GUI only supports a maximum of 26 states for visual reasons");
	            a.show();
	        }
	        
	      
	    }
	    public static String[] getVariables(){
	    	String[] Variables = new String[rows];
	    
	        for (int i = 0; i < TransformationMatrix.length; i++) {
	           Variables [i] =  TransformationMatrix[i][0].getText();
	           
	        }
	    	
	    	return Variables;
	    }

	    
	    public static String[] getProductions(){

	    	String[] Productions = new String[rows];
	        for (int i = 0; i < TransformationMatrix.length; i++) {
	           Productions [i] =  TransformationMatrix[i][2].getText();
	           
	        }
	    	
	    	return Productions;
	    }
}

package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Los imports de la libreria externa para mostrar los grafos de manera visual
 */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Grammar;


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
	    
	    @FXML
	    private TextArea ProduccionesTA;


	    public static int columns;
	    
	    public static int rows;
	    
	    public static TextField [][] TransformationMatrix;
	    public static GridPane InputGrid;
	    
	    private static String VariablesS;
	    
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
	         TransformationMatrix = new TextField[rows][rows];
	          
	          //Se Colocan los indices superiores en el GridPane de Entrada

	          
	              TextField HeaderTextField = new TextField("Variable");
	              HeaderTextField.setEditable(false);
	              HeaderTextField.setPrefWidth(80);
	              InputGrid.add(HeaderTextField, 0, 0);
	          

	              HeaderTextField = new TextField("---->");
	              HeaderTextField.setEditable(false);
	              HeaderTextField.setPrefWidth(80);
	           
	              InputGrid.add(HeaderTextField, 1, 0);
	              
	              
	              HeaderTextField = new TextField("produciones");
	              HeaderTextField.setEditable(false);
	              HeaderTextField.setPrefWidth(80);
	              InputGrid.add(HeaderTextField, 2, 0);
	          
	          //Se colocan todas las Textfields en el GridPane dependiendo del tamano de las entradas
	               for (int i = 1; i < rows+1; i++) {
	               TextField CoordinateTextField = new TextField();
	            
	                CoordinateTextField.setPrefWidth(80);
	                    
	                  InputGrid.add(CoordinateTextField, 0, i);
	                 TransformationMatrix[0][i - 1] = CoordinateTextField;
	                  
	                           for (int j = 1; j < columns+1 ; j++) {
	                        	   
	                     if(j == 1) {   	   
	                    	 TextField CoordinateTextField1= new TextField();
		                      CoordinateTextField1.setPrefWidth(80);
		                      CoordinateTextField1.setText("---->");;
		                      CoordinateTextField1.setEditable(false);
		                             
		                                 InputGrid.add(CoordinateTextField1, j, i);
	                                 
	                     }
	                     else {
	                    	 TextField CoordinateTextField1= new TextField();
		                      CoordinateTextField1.setPrefWidth(80);
		                      CoordinateTextField1.setText("");;
		                      
		                               TransformationMatrix[j - 1][i - 1] = CoordinateTextField1;
		                                 InputGrid.add(CoordinateTextField1, j, i);
	                     }
	                     
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
	    public static ArrayList<String> getVariables(){
	    	
	    	ArrayList<String> Variables =new  ArrayList<String>();
	        for (int i = 0; i < rows; i++) {
	        	if(TransformationMatrix[1][i] != null) {
	        	if( TransformationMatrix[1][i].getText() != null ) {
	           Variables.add( TransformationMatrix[0][i].getText());
	           
	           if(VariablesS != null) {
	        	   VariablesS += TransformationMatrix[0][i].getText() + " ";
	           }else {
	        	   VariablesS = TransformationMatrix[0][i].getText()+ " ";
	           }
	         
	        	}
	        	}
	        }
	    	
	    	return Variables;
	    }

	    
	    public static ArrayList<String> getTerminals(){

	    	ArrayList<String> Terminals = new ArrayList<String>();
	        for (int i = 0; i < rows; i++) {
	        if( TransformationMatrix[1][i] != null) {
	        	if( TransformationMatrix[1][i].getText() != null ) {
	           Terminals.add(  TransformationMatrix[1][i].getText());
	        	}
	        }
	        }
	    	
	    	return Terminals;
	    }
	    // ESte es el metodo que puedes utilzar para crear el txt para leerlo asumo que devuelve la direccion del archivo
	    public  String generateTxtGrammer(){
	    	
	    	return "La direccion del archivo";
	    }
	    
	    public void print (ArrayList<String> Terminals) {
	    	for (int i = 0; i < Terminals.size(); i++) {
		           System.out.println(Terminals.get(i));
		           
		        }
	    }
	    
	    
	    // Aqui es donde se creari la gramatica y se comprueba si la cadena es producida por la gramatica
	public void CalculalteGrammer() {
    	ArrayList<String> Terminals = getTerminals();
    	
    	ArrayList<String> Variables =  getVariables();
    	
    	String GrammerDirection = generateTxtGrammer();
    	
    	String terminals = ProduccionesTA.getText();
    	
    	
    	Grammar Gm = new Grammar(Variables.get(0),terminals,VariablesS,GrammerDirection);
    	//print(Terminals);
    	//print(Variables);
    	//System.out.println(VariablesS);
    	
	String Message = Gm.CYK(CadenaTA.getText());
	 Alert a = new Alert(Alert.AlertType.CONFIRMATION);
     a.setContentText(Message);
     a.show();
    	
		
	}
}

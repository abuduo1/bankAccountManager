package bigWork;

import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


class Transaction {
	
	private Date date=new Date();
	private double amount;
	private double balan;
	private String description;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalan() {
		return balan;
	}

	public void setBalan(double balan) {
		this.balan = balan;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Transaction(Date date,double amount,double balan,String description) {
		this.date=date;
		this.amount=amount;
		this.balan=balan;
		this.description=description;		
	}
	
	public String toString() {
		return "\nthe Account "+description+" "+amount+" at \n"+date+"\n the amount is "+balan+" now\n\n";
	}
	
}


class Account{
	private String id="";
	private String name="";
	private double balance=0;
	private double annualInterestRate=0;
	private Date dateCreated=new Date();
	private ArrayList<Transaction> transaction=new ArrayList<>();
	
	public Account(){
	}
	
	public Account(String Name,String Id,double Balance,double AnnualInterestRate){
		id=Id;
		name=Name;
		balance=Balance;
		annualInterestRate=AnnualInterestRate;
		transaction.add(new Transaction(new Date(),Balance,getBalance(),"save money"));
	}
	
	public ArrayList<Transaction> getTransaction() {
		return transaction;
	}
	
	public void setTransaction(ArrayList<Transaction> transaction) {
		this.transaction=transaction;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String newId) {
		id=newId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name=newName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double newBalance) {
		balance=newBalance;
	}
	
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	public void setAnnualInterestRate(double newannualInterestRate) {
		annualInterestRate=newannualInterestRate;
	}
	
	public Date getdateCreate() {
		return dateCreated;
	}
	
	public double getMonthlyInterestRate() {
		return 100*annualInterestRate/12;
	}
	
	double getMonthlyInterest() {
		return balance*annualInterestRate/1200;
	}
	
	double getAnnualInterest() {
		return balance*annualInterestRate/100;
	}
	
	public void withDraw(double num) {
		balance=balance-num;
		transaction.add(new Transaction(new Date(),num,getBalance(),"cut money"));
	}
	
	public void deposit(double num) {
		balance=balance+num;
		transaction.add(new Transaction(new Date(),num,getBalance(),"save money"));
	}
	
	public String toString() {
		return id+" Account opearte at "+dateCreated;
	}
	
	public String isOverdraw() {  //表示是否透支
		if(balance<=-100) return "Your account is overdraw,please pay off quickly.";
		else return "your account isn't overdraw,and the overdarw limit is ＄100.";
	}
	
}



public class bankAccountManger extends Application {
	
	    public Account a;

		public static void main(String[] args) {
			Application.launch(args);
		}
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			BorderPane pane = new BorderPane();
			
			Text Text = new Text("\n\n\n\tWelcome to our bank,\n"
					+ "\tEnter your account's information now:");			
			Button buttonStart = new Button("Start");			
			pane.setTop(Text);
			pane.setCenter(buttonStart);	
			
			buttonStart.setOnAction(e->{
				pane.getChildren().clear();
				pane.getChildren().add(new GreatAccount());
			});
						
			Scene scene = new Scene(pane, 400, 250);
			primaryStage.setTitle("Bank");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}

		
		class GreatAccount extends Pane{
			
			GridPane pane = new GridPane();
            
			public GreatAccount() {
		    pane.setPadding(new Insets(15,15,15,15));
		    Button Next=new Button("Next");
		    pane.add(Next, 1, 8);
		    
			TextField t1=new TextField();
			TextField t2=new TextField();
			TextField t3=new TextField();
			TextField t4=new TextField();
			
			pane.add(new Text("Name:\t"), 0, 0);
			pane.add(new Text("Id:\t"), 0, 1);
			pane.add(new Text("balance:\t"), 0, 2);
			pane.add(new Text("annualRate:\t"), 0, 3);
			
			pane.add(t1, 1, 0);
			pane.add(t2, 1, 1);
			pane.add(t3, 1, 2);
			pane.add(t4, 1, 3);
			
			getChildren().add(pane);
					
			Next.setOnAction(e->{
				try {
					a=new Account(t1.getText(),t2.getText(),
							Double.parseDouble(t3.getText()),Double.parseDouble(t4.getText()));
					}catch(Exception ex) {
						   System.out.print("the enter is defult.\n");
						   return;
				    }
				pane.getChildren().clear();
				pane.getChildren().add(new StartOperate());
			});			
		  }
			
		}
		
		class StartOperate extends Pane{
			
            Pane pane = new Pane();
			
			public StartOperate() {
			pane.setPadding(new Insets(15,15,15,15));		
			Text text = new Text("Are you ready for openning the Account?（Y/N）\r\n");
			text.setX(50);
			text.setY(50);
			Button buttonY = new Button("Yes");
			buttonY.setLayoutX(100);
			buttonY.setLayoutY(200);
			Button buttonN = new Button("NO");
			buttonN.setLayoutX(200);
			buttonN.setLayoutY(200);
			pane.getChildren().addAll(text,buttonY,buttonN);
			getChildren().add(pane);
			
			buttonY.setOnAction(e->{
				pane.getChildren().clear();
				pane.getChildren().add(new Menu());
			});
						
			buttonN.setOnAction(e->{
				pane.getChildren().clear();
				Text textEnd = new Text("Operating is over! Bye !");
				textEnd.setX(50);
				textEnd.setY(150);
				pane.getChildren().add(textEnd);
			});
			
		}
		}			
			
		class Menu extends Pane{
			GridPane gridpane = new GridPane();
			
			public Menu() {
	        
			gridpane.setPadding(new Insets(15,15,15,15));
			
			Button bt1 = new Button("√");
			Button bt2 = new Button("√");
			Button bt3 = new Button("√");
			Button bt4 = new Button("√");
			Button bt5 = new Button("√");
			Button bt6 = new Button("√");
			Button bt7 = new Button("√");

			gridpane.add(new Text("The bank's menu:"), 0, 0);
			gridpane.add(new Text("1:Check the account"), 0, 1);
			gridpane.add(bt1, 1, 1);
			gridpane.add(new Text("2:Deposit money"), 0, 2);
			gridpane.add(bt2, 1, 2);
			gridpane.add(new Text("3:Withdraw money"), 0, 3);
			gridpane.add(bt3, 1, 3);
			gridpane.add(new Text("4:Print transaction's bill"), 0, 4);
			gridpane.add(bt4, 1, 4);
			gridpane.add(new Text("5:Check wether overdraw"), 0, 5);
			gridpane.add(bt5, 1, 5);
			gridpane.add(new Text("6:Check the profit"), 0, 6);
			gridpane.add(bt6, 1, 6);
			gridpane.add(new Text("7:End operation"), 0, 7);
			gridpane.add(bt7, 1, 7);
			
			getChildren().add(gridpane);
			
			bt1.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new Operate1());
			});
			
			bt2.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new Operate2());
			});
			
			bt3.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new Operate3());
			});
			
			bt4.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new Operate4());
			});
			
			bt5.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new Operate5());
			});
			
			bt6.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new Operate6());
			});
			
			bt7.setOnAction(e->{
				gridpane.getChildren().clear();
				gridpane.getChildren().add(new endOperate());
			});
			
			}
		}

		class Operate1 extends Pane{
			
			BorderPane pane = new BorderPane();
			
			public Operate1() {
				Label label=new Label("\nFind Account succeed.\n"+a.toString()
				    +"\nReturn the main menu......\n\n\n");
				label.setTextFill(Color.BLUE);
				pane.setCenter(label);
				
				Button Return=new Button("Next");
				Return.setTextFill(Color.GREEN);
				pane.setBottom(Return);
				
				getChildren().add(pane);
				
				Return.setOnAction(e->{
					pane.getChildren().clear();
					pane.getChildren().add(new Menu());
				});
			}
			
		}		
		
        class Operate2 extends Pane{
        	
			VBox pane = new VBox();
			
			public Operate2() {
				Label label1=new Label("Enter the deposit money:\n");
				TextField tf=new TextField();
				Label label2=new Label("\nOperate succeed.Return the main menu......\n\n\n");
				Button Return=new Button("Next");
				Return.setTextFill(Color.GREEN);
							    
				pane.getChildren().addAll(label1,tf,label2,Return);
				
				getChildren().add(pane);
				
				Return.setOnAction(e->{
					a.deposit(Double.parseDouble(tf.getText()));
					pane.getChildren().clear();
					pane.getChildren().add(new Menu());
				});
			}			
		}		

        
        class Operate3 extends Pane{
        	
			VBox pane = new VBox();
			
			public Operate3() {
				Label label1=new Label("Enter the withdraw money:\n");
				TextField tf=new TextField();
				Label label2=new Label("\nOperate succeed.Return the main menu......\n");
				Button Return=new Button("Next");
				Return.setTextFill(Color.GREEN);
				
				pane.getChildren().addAll(label1,tf,label2,Return);
				
				getChildren().add(pane);
				
				Return.setOnAction(e->{
				    a.withDraw(Double.parseDouble(tf.getText()));
					pane.getChildren().clear();
					pane.getChildren().add(new Menu());
				});
			}			
		}		


		class Operate4 extends Pane{
			
			BorderPane pane = new BorderPane();
			
			public Operate4() {
				Label label=new Label("Show transactions succeed as belows.\n"
			+a.getTransaction().toString()+"\n\nOperate succeed.Return the main menu......\n\n\n");
				label.setTextFill(Color.BLUE);
				pane.setLeft(label);
				
				Button Return=new Button("Next");
				Return.setTextFill(Color.GREEN);
				pane.setBottom(Return);
				
				pane.setMaxHeight(400);
				pane.setMaxWidth(500);
				
				getChildren().add(pane);
				
				Return.setOnAction(e->{
					pane.getChildren().clear();
					pane.getChildren().add(new Menu());
				});
			}			
		}
		
		
		class Operate5 extends Pane{
			
			BorderPane pane = new BorderPane();
			
			public Operate5() {
				Label label=new Label("Now check the balance.\n"+a.isOverdraw());
				pane.setCenter(label);
				
				Button Return=new Button("Next");
				Return.setTextFill(Color.GREEN);
				pane.setBottom(Return);
				
				getChildren().add(pane);
				
				Return.setOnAction(e->{
					pane.getChildren().clear();
					pane.getChildren().add(new Menu());
				});
			}			
		}
		
        
        class Operate6 extends Pane{
        	
			VBox pane = new VBox();
			
			public Operate6() {
				Label label1=new Label("\n|the monthly Interest Rate is "+a.getMonthlyInterestRate());
				Label label2=new Label("\n|the monthly increasing profit is "+a.getMonthlyInterest());
				Label label3=new Label("\n|the annually Interest Rate is "+a.getAnnualInterestRate());
				Label label4=new Label("\n|the annually increasing profit is "+a.getAnnualInterest());
				Label label5=new Label("\nOperate succeed.Return the main menu......\n\n");
				
				Button Return=new Button("Next");
				Return.setTextFill(Color.GREEN);
				
				pane.getChildren().addAll(label1,label2,label3,label4,label5,Return);				
				getChildren().add(pane);
				
				Return.setOnAction(e->{
					pane.getChildren().clear();
					pane.getChildren().add(new Menu());
				});
			}			
		}	        
        
		class endOperate extends Pane{
			
			BorderPane pane = new BorderPane();
			
			public endOperate() {
				Label label=new Label("Ending work succeed.\nByeBye!\n^-^\n");
				label.setTextFill(Color.GREEN);
				pane.setCenter(label);
				getChildren().add(pane);
			}			
		}
		
		
	}

		

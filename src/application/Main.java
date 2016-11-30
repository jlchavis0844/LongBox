package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.ws.Action;

import javafx.geometry.Rectangle2D;

import javafx.scene.Group;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import localDB.LocalDB;
import model.Issue;
import model.Volume;
import scenes.AddComic;
import scenes.DetailView;
import scenes.IssuePreview;
import scenes.IssueResult;
import scenes.VolResult;
import scenes.VolumePreview;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import org.json.JSONException;
import javafx.stage.Screen;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.application.Platform;
import javafx.scene.input.*;
import javafx.scene.input.KeyCombination;

@SuppressWarnings({ "restriction", "unused" })
public class Main extends Application {
	@SuppressWarnings("rawtypes")
	private Stage window;
	private ListView<VolResult> list;
	private ListView<IssueResult> issueList;
	private static ArrayList<Issue> added;
	private static ArrayList<Issue> allIssues;
	private static ArrayList<Volume> allVols;
	private static List<IssuePreview> prevs;
	private static List<VolumePreview> volPreviews;
	private static ListView<IssuePreview> leftList; // need to sort this
	private static ListView<VolumePreview> volListView; // need to sort this
	private static ObservableList<IssuePreview> obvRes;
	private static ObservableList<VolumePreview> volObvRes;
	private static ScrollPane leftScroll;
	private HBox hbox;
	private Button addButton;
	private static TreeView treeView;
	private static ArrayList<Issue> searchResultIssues;

	Stage stage;
	Scene defaultScene, scene2;
	Scene searchSceneByIssue, searchSceneByVolume, loadScene;

	public static final int SCREEN_WIDTH = (int) Screen.getPrimary().getVisualBounds().getWidth();
	public static final int SCREEN_HEIGHT = (int) Screen.getPrimary().getVisualBounds().getHeight();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// public void genericStart(Stage primaryStage) throws Exception {
	public void genericStart(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Digital Long Box");
		layout = new BorderPane();

		added = new ArrayList<Issue>();

		System.out.println("getting all issues");
		Long start;
		start = System.currentTimeMillis();
		allIssues = LocalDB.getAllIssues();
		System.out.println("Done loading after " + (System.currentTimeMillis() - start));

		if (allIssues == null) {
			System.out.println("no issues found ");
			allIssues = new ArrayList<Issue>();
		}

		start = System.currentTimeMillis();
		allVols = LocalDB.getAllVolumes();
		System.out.println("volume loading took " + (System.currentTimeMillis() - start));

		if (allVols == null) {
			allVols = new ArrayList<>();
		}
		volPreviews = new ArrayList<VolumePreview>();

		for (Volume v : allVols) {
			volPreviews.add(new VolumePreview(v, allIssues));
		}

		treeView = new TreeView<VolumePreview>(buildRoot());
		treeView.setPrefWidth(500);
		treeView.setPrefHeight(950);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
				if (newValue instanceof VolumeCell) {
					if (!((VolumeCell) newValue).isFilled())
						try {
							((VolumeCell) newValue).setIssues(allIssues);
						} catch (JSONException ex) {
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
				} else {
					TreeItem<IssuePreview> ti = (TreeItem<IssuePreview>) treeView.getSelectionModel().getSelectedItem();
					if (ti.getValue().getmIssue() != null) {
						Issue issue = ti.getValue().getmIssue();
						try {
							layout.setRight(new DetailView(issue));
						} catch (JSONException ex) {
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
					} else
						System.out.println("something went wrong loading issue");
				}
				boolean expanded = ((TreeItem) treeView.getSelectionModel().getSelectedItem()).isExpanded();
				newValue.setExpanded(!expanded);
			}

		});

		leftScroll = new ScrollPane();
		leftScroll.setPrefHeight(1000);
		leftScroll.setMaxHeight(1080);
		// leftScroll.setContent(volListView);
		leftScroll.setContent(treeView);
		leftScroll.setPadding(new Insets(10));

		layout.setLeft(leftScroll);
		hbox = new HBox();
		hbox.setPadding(new Insets(10));
		addButton = new Button("Click here to add");
		hbox.getChildren().add(addButton);
		layout.setTop(hbox);

		addButton.setOnAction(e -> {
			new AddComic(added);
			try {
				updateLeft();
			} catch (JSONException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

		Scene scene = new Scene(layout, 1900, 1050);
		window.setScene(scene);
		window.show();
		System.out.println("Done loading after " + (System.currentTimeMillis() - start));
	}

	public static void updateLeft() throws JSONException {
		for (Issue i : added) {

			if (!allIssues.contains(i)) {
				LocalDB.addIssue(i);
				allIssues.add(i);
				int foundIndex = -1;
				for (int j = 0; j < volPreviews.size(); j++) {
					if (volPreviews.get(j).getVolName().equals(i.getVolumeName())) {
						foundIndex = j;
						volPreviews.get(j).update(allIssues);
					}
				}

				if (foundIndex == -1) {
					volPreviews.add(new VolumePreview(i.getVolume(), allIssues));
				}
			} // end if

		} // end for
		treeView.setRoot(buildRoot());
		added.clear();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TreeItem buildRoot() {
		TreeItem root = new TreeItem<VolumePreview>();

		root.setValue("Volumes");
		root.setExpanded(true);
		for (VolumePreview volumePreview : volPreviews) {
			// vp.update(allIssues);
			TreeItem temp = new VolumeCell(volumePreview);
			root.getChildren().add(temp);
		}
		return root;
	}


	
	//--------------------------------------
	
	@Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        //primaryStage.setTitle("Hello World!");

        setDefaultScene();

        setScene2();
        
        setLoadScene();

        //create MenuBar
        MenuBar menuBar = createMenuBar();
        // bind MenuBar's with property and the Stage's
        menuBar.prefWidthProperty().bind(stage.widthProperty());

        //stage.setScene(defaultScene);
        stage.setScene(loadScene);
        stage.show();
    }

//    Button scene1Button, scene2Button;
//    public void ButtonClicked(ActionEvent e) {
//        if (e.getSource() == scene1Button) {
//            stage.setScene(scene2);
//        } else {
//            stage.setScene(defaultScene);
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setDefaultScene() {

        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        root.setTop(menuBar);

        Label label = new Label("Default Scene");

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(10);
        //set background color of each Pane
        flowPane.setStyle("-fx-padding: 10px;");
        //add everything to panes
        flowPane.getChildren().addAll(label);

        root.setCenter(flowPane);

        //root.getChildren().addAll(root, flowPane);
        //make 2 scenes from 2 panes
        defaultScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setScene2() {

        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        root.setTop(menuBar);

        Label label = new Label("Scene 2");

        FlowPane flowpane = new FlowPane();
        flowpane.setVgap(10);
        flowpane.setStyle("-fx-background-color: red;-fx-padding: 10px;");
        flowpane.getChildren().addAll(label);

        root.setCenter(flowpane);

        scene2 = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setSearchSceneByVolume() {

        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        root.setTop(menuBar);

        //-----------------------add code below this line-------------------
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(80);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        gridpane.getColumnConstraints().addAll(column1, column2,column3);
	
        Label label = new Label("Search By Volume");
        Label inputLabel = new Label("Type In Keyword");
        TextField inputTextField = new TextField();
        Button searchButton = new Button("Search");
        
        searchButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                
            	try {
					searchResultIssues = LocalDB.searchIssueByName(inputTextField.getText());
					debug("input string is " + inputTextField.getText());
					debug("size of result is " + searchResultIssues.size());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	debug("input is " + inputTextField.getText());
                
            }
        });
		
		
        
        gridpane.add(label, 0, 0);
        gridpane.add(inputLabel, 0, 1);
        gridpane.add(inputTextField, 1, 1);
        gridpane.add(searchButton, 2, 1);

        root.setCenter(gridpane);

        searchSceneByVolume = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setSearchSceneByIssue() throws JSONException, SQLException {
    	
        BorderPane rootBorderPane = new BorderPane();

        MenuBar menuBar = createMenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        rootBorderPane.setTop(menuBar);

        //-----------------------add code below this line-------------------
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(80);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        gridpane.getColumnConstraints().addAll(column1, column2,column3);
	
        Label label = new Label("Search By Issue");
        Label inputLabel = new Label("Type In Keyword");
        TextField inputTextField = new TextField();
        Button searchButton = new Button("Search");
        
        searchButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                
            	try {
					searchResultIssues = LocalDB.searchIssueByName(inputTextField.getText());
					debug("input string is " + inputTextField.getText());
					debug("size of result is " + searchResultIssues.size());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	debug("input is " + inputTextField.getText());
                
            }
        });
		
		
        
        gridpane.add(label, 0, 0);
        gridpane.add(inputLabel, 0, 1);
        gridpane.add(inputTextField, 1, 1);
        gridpane.add(searchButton, 2, 1);
        
        /*
        volPreviews = new ArrayList<VolumePreview>();

		for (Volume v : allVols) {
			volPreviews.add(new VolumePreview(v, searchResultIssues));
		}

		treeView = new TreeView<VolumePreview>(buildRoot());
		treeView.setPrefWidth(500);
		treeView.setPrefHeight(950);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
				if (newValue instanceof VolumeCell) {
					if (!((VolumeCell) newValue).isFilled())
						try {
							((VolumeCell) newValue).setIssues(allIssues);
						} catch (JSONException ex) {
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
				} else {
					TreeItem<IssuePreview> ti = (TreeItem<IssuePreview>) treeView.getSelectionModel().getSelectedItem();
					if (ti.getValue().getmIssue() != null) {
						Issue issue = ti.getValue().getmIssue();
						try {
							root.setRight(new DetailView(issue));
						} catch (JSONException ex) {
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
					} else
						System.out.println("something went wrong loading issue");
				}
				boolean expanded = ((TreeItem) treeView.getSelectionModel().getSelectedItem()).isExpanded();
				newValue.setExpanded(!expanded);
			}

		});

		leftScroll = new ScrollPane();
		leftScroll.setPrefHeight(1000);
		leftScroll.setMaxHeight(1080);
		// leftScroll.setContent(volListView);
		leftScroll.setContent(treeView);
		leftScroll.setPadding(new Insets(10));
		
		BorderPane viewBorderPane = new BorderPane();
		viewBorderPane.setLeft(leftScroll);
		viewBorderPane.setCenter(treeView);
        */
		//----------------------------------------------
        
        VBox vb = new VBox();
        vb.setSpacing(5);
        //vb.getChildren().add(gridpane);
        //viewBorderPane);
        
        //root.setCenter(vb);
        rootBorderPane.setCenter(gridpane);
        
        //--------------	


        searchSceneByIssue = new Scene(rootBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private Menu createMenuSearch() {
        // Cameras menu - camera 1, camera 2
        Menu tools = new Menu("Search");

        MenuItem searchByIssueNameMenuItem = new MenuItem("Search By Issue Name");
        searchByIssueNameMenuItem.setMnemonicParsing(true);
        searchByIssueNameMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.SHORTCUT_DOWN));
        searchByIssueNameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				try {
					setSearchSceneByIssue();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                stage.setScene(searchSceneByIssue);
            }
        });

        MenuItem searchByVolumeNameMenuItem = new MenuItem("Search By Volume Name");
        searchByVolumeNameMenuItem.setMnemonicParsing(true);
        searchByVolumeNameMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
        searchByVolumeNameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setSearchSceneByVolume();
                stage.setScene(searchSceneByVolume);
            }
        });

        tools.getItems().addAll(searchByIssueNameMenuItem, searchByVolumeNameMenuItem);

        return tools;
    }

    private Menu createMenuAdd() {
        // Cameras menu - camera 1, camera 2
        Menu tools = new Menu("Add");

        MenuItem addIssueMenuItem = new MenuItem("Search By Issue Name");
        addIssueMenuItem.setMnemonicParsing(true);
        addIssueMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
        addIssueMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                debug("add issue here...");
            }
        });

        MenuItem addVolumeMenuItem = new MenuItem("Search By Volume Name");
        addVolumeMenuItem.setMnemonicParsing(true);
        addVolumeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        addVolumeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                debug("add volume Name here...");
            }
        });

        tools.getItems().addAll(addIssueMenuItem, addVolumeMenuItem);

        return tools;
    }

    private Menu createMenuFile() {
        // add MenuItem object to MenuBar
        // File menu - new, save, exit
        Menu menu = new Menu("File");

        MenuItem menuItemLoadAllComicBooks = new MenuItem("Load All Comic Books");
        menuItemLoadAllComicBooks.setMnemonicParsing(true);
        menuItemLoadAllComicBooks.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN));
        menuItemLoadAllComicBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	stage.setScene(loadScene);
            }
        });

        MenuItem menuItemSave = new MenuItem("Save");
        menuItemSave.setMnemonicParsing(true);
        menuItemSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setMnemonicParsing(true);
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        menu.getItems().add(0, menuItemLoadAllComicBooks);
        menu.getItems().add(1, menuItemSave);
        menu.getItems().add(2, exitMenuItem);

        return menu;
    }

    private Menu createMenuHelp() {
        // add MenuItem object to MenuBar
        // File menu - new, save, exit
        Menu menu = new Menu("Help");

        MenuItem menuItemLoadAbout = new MenuItem("About");
        menuItemLoadAbout.setMnemonicParsing(true);
        menuItemLoadAbout.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN));

        MenuItem menuItemHelp = new MenuItem("Help");
        menuItemHelp.setMnemonicParsing(true);
        menuItemHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN));

        menu.getItems().addAll(menuItemLoadAbout, menuItemHelp);

        return menu;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menu = createMenuFile();
        menuBar.getMenus().add(menu);

        Menu add = createMenuAdd();
        menuBar.getMenus().add(add);

        Menu search = createMenuSearch();
        menuBar.getMenus().add(search);

        Menu help = createMenuHelp();
        menuBar.getMenus().add(help);
        return menuBar;
    }

    private static void debug(String input) {
        System.out.println(input);
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// public void genericStart(Stage primaryStage) throws Exception {
	public void setLoadScene() throws Exception {
		//window = primaryStage;
		//window.setTitle("Digital Long Box");
		BorderPane rootBorderPane = new BorderPane();
		
        MenuBar menuBar = createMenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        rootBorderPane.setTop(menuBar);

		added = new ArrayList<Issue>();

		System.out.println("getting all issues");
		Long start;
		start = System.currentTimeMillis();
		allIssues = LocalDB.getAllIssues();
		System.out.println("Done loading after " + (System.currentTimeMillis() - start));

		if (allIssues == null) {
			System.out.println("no issues found ");
			allIssues = new ArrayList<Issue>();
		}

		start = System.currentTimeMillis();
		allVols = LocalDB.getAllVolumes();
		System.out.println("volume loading took " + (System.currentTimeMillis() - start));

		if (allVols == null) {
			allVols = new ArrayList<>();
		}
		volPreviews = new ArrayList<VolumePreview>();

		for (Volume v : allVols) {
			volPreviews.add(new VolumePreview(v, allIssues));
		}

		treeView = new TreeView<VolumePreview>(buildRoot());
		treeView.setPrefWidth(500);
		treeView.setPrefHeight(950);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
				if (newValue instanceof VolumeCell) {
					if (!((VolumeCell) newValue).isFilled())
						try {
							((VolumeCell) newValue).setIssues(allIssues);
						} catch (JSONException ex) {
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
				} else {
					TreeItem<IssuePreview> ti = (TreeItem<IssuePreview>) treeView.getSelectionModel().getSelectedItem();
					if (ti.getValue().getmIssue() != null) {
						Issue issue = ti.getValue().getmIssue();
						try {
							rootBorderPane.setRight(new DetailView(issue));
						} catch (JSONException ex) {
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
					} else
						System.out.println("something went wrong loading issue");
				}
				boolean expanded = ((TreeItem) treeView.getSelectionModel().getSelectedItem()).isExpanded();
				newValue.setExpanded(!expanded);
			}

		});

		leftScroll = new ScrollPane();
		leftScroll.setPrefHeight(1000);
		leftScroll.setMaxHeight(1080);
		// leftScroll.setContent(volListView);
		leftScroll.setContent(treeView);
		leftScroll.setPadding(new Insets(10));

		rootBorderPane.setLeft(leftScroll);
		hbox = new HBox();
		hbox.setPadding(new Insets(10));
		addButton = new Button("Click here to add");
		hbox.getChildren().add(addButton);
		rootBorderPane.setBottom(hbox);

		addButton.setOnAction(e -> {
			new AddComic(added);
			try {
				updateLeft();
			} catch (JSONException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

		loadScene = new Scene(rootBorderPane, 1900, 1050);
		//window.setScene(scene);
		//window.show();
		//System.out.println("Done loading after " + (System.currentTimeMillis() - start));
	}

}
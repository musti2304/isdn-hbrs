package view;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.IAbstractOrt;
import model.IOrtMitBesuchsdatum;
import model.IOrtsListe;
import model.javapersistence.Ort;
import model.javapersistence.OrtMitBesuchsdatum;
import model.javapersistence.OrtsListe;
import strategy.OeffentlicherModusStrategy;
import strategy.PrivatModusStrategy;
import strategy.Strategy;

@SuppressWarnings("restriction")
public class OrtsListenAnsicht implements Serializable  {

	private static ObservableList<IAbstractOrt> tableViewItems = FXCollections.observableArrayList();
	private static IOrtsListe ortsListe;
	private IAbstractOrt abstractOrt;
	private Strategy strategie;
	private static final int SIZE_OF_OTHER_CONTROLS = 105;

	//////////////////// Constructor /////////////////////
	public OrtsListenAnsicht() {}
	
	public OrtsListenAnsicht(IOrtsListe ortsListe) {
		this.ortsListe = ortsListe;
		//ortsListe.addObserver(this);
		ortsListe.addOrt(abstractOrt);
	}


	/////////////////////// UI Elements ////////////////////////
	BorderPane borderPane = new BorderPane();
	Scene scene = new Scene(borderPane);

	TableView<IAbstractOrt> table = new TableView<>();
	TableColumn<IAbstractOrt, String> nameCol = new TableColumn<IAbstractOrt, String>("Name");
	TableColumn<IAbstractOrt, String> anschriftCol = new TableColumn<IAbstractOrt, String>("Anschrift");
	TableColumn<IAbstractOrt, Date> dateCol = new TableColumn<IAbstractOrt, Date>("Zuletzt besucht am");

	Image image = new Image(
			"http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x405&maptype=mapnik", true);

	ImageView imageview = new ImageView();

	Button btnAdd = new Button("Ort hinzufügen");
	Button btnSave = new Button("Speichern");
	Button btnAddDate = new Button("Ort mit Datum hinzufügen");
	Button btnShowPlaces = new Button("Orte ausgeben");
	Button btnDel = new Button("Ort löschen");
	Button btnUndo = new Button("Rückgängig");
	Button btnLoad = new Button("Laden");

	final ToggleGroup group = new ToggleGroup();

	final RadioButton oeffentlicherModusRB = new RadioButton();
	final RadioButton privatModusRB = new RadioButton();
	final OrtsListenAnsicht ortsListenAnsicht = this;
	HBox hbox = new HBox();
	HBox strategyHBox = new HBox();

	public void show(final Stage primaryStage) {
		primaryStage.setTitle("My POIs");

		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		anschriftCol.setMinWidth(300);
		anschriftCol.setCellValueFactory(new PropertyValueFactory<>("anschrift"));
		dateCol.setMinWidth(200);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("datumDesBesuchs"));

		table.getColumns().addAll(nameCol, anschriftCol, dateCol);
		table.setItems(tableViewItems);

		imageview.setImage(image);

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				Image newSelectionimage = new Image(
						getMapURL(newSelection.getAnschrift(), (int) borderPane.getHeight() - SIZE_OF_OTHER_CONTROLS),
						false);
				imageview.setImage(newSelectionimage);
			}
		});

		borderPane.setRight(imageview);

		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: linear-gradient(#6699CC, #104E8B);");
		hbox.getChildren().addAll(btnAdd, btnAddDate, btnSave, btnDel, btnUndo,btnLoad);

		borderPane.setCenter(table);
		borderPane.setBottom(hbox);
		borderPane.setTop(strategyHBox);

		oeffentlicherModusRB.setToggleGroup(group);
		oeffentlicherModusRB.setSelected(true);
		oeffentlicherModusRB.setText("Öffentlicher Modus");

		privatModusRB.setToggleGroup(group);
		privatModusRB.setText("Privater Modus");

		strategyHBox.setPadding(new Insets(15, 12, 15, 12));
		strategyHBox.setSpacing(2);
		strategyHBox.setStyle("-fx-background-color: linear-gradient(#ffff33, #808000);");
		strategyHBox.getChildren().addAll(oeffentlicherModusRB, privatModusRB, btnShowPlaces);

		this.strategie = new OeffentlicherModusStrategy();

		primaryStage.setMinWidth(1200);
		primaryStage.setMinHeight(500);

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				(new OrtsAnsicht(new Ort(), ortsListe, ortsListenAnsicht)).show(primaryStage);
			}
		});
		
		btnAddDate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				(new OrtMitBesuchsdatumAnsicht(new OrtMitBesuchsdatum(), ortsListe, ortsListenAnsicht))
						.show(primaryStage);
			}
		});

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			List<IAbstractOrt> listeVonOrten;

			@Override
			public void handle(ActionEvent e) {
				OrtsListe.getInstance().save();
			}
		});
		btnLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				OrtsListe.getInstance().load();
			}
		});

		///////////////// Button Actions and Handlers //////////////////
		btnDel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				IAbstractOrt abstractOrt = table.getSelectionModel().getSelectedItem();
//				if (abstractOrt == null)
//					return;
				ortsListe.removeOrt(abstractOrt);
				Image image = new Image(
						"http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x405&maptype=mapnik",
						true);
				imageview.setImage(image);

				ortsListe.removeOrt(abstractOrt);
				ortsListenAnsicht.update(); //ortsListe, this
			}
		});
		
		btnUndo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO undo funktion implementieren
			}
		});

		btnShowPlaces.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				strategie.ausgabeDerOrte(ortsListe.getListeVonOrten());
			}
		});

		// Handler for the double mouse click
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						IAbstractOrt abstractOrt = table.getSelectionModel().getSelectedItem();
						if (abstractOrt instanceof IOrtMitBesuchsdatum) {
							new OrtMitBesuchsdatumAnsicht(abstractOrt, ortsListe, ortsListenAnsicht).show(primaryStage);
						} else {
							new OrtsAnsicht(abstractOrt, ortsListe, ortsListenAnsicht).show(primaryStage);
						}
					}
				}
			}
		});

		// Radio button handler
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {
					if (group.getSelectedToggle().equals(oeffentlicherModusRB)) {
						strategie = new OeffentlicherModusStrategy();
						System.out.println("Öffentlicher Modus aktiviert");
					} else {
						strategie = new PrivatModusStrategy();
						System.out.println("Privater Modus aktiviert");
					}
				}
			}
		});

		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(OrtsListenAnsicht.class.getResourceAsStream("icon.png")));
		updateDisplayedList();
		primaryStage.show();

		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				String anschrift = table.getSelectionModel().getSelectedItem() != null
						? table.getSelectionModel().getSelectedItem().getAnschrift() : null;
				Image newSelectionimage = new Image(
						getMapURL(anschrift, (int) borderPane.getHeight() - SIZE_OF_OTHER_CONTROLS), false);
				imageview.setImage(newSelectionimage);
			}
		});
	}

	public static void updateDisplayedList() {
		tableViewItems.clear();
		
		tableViewItems.addAll(ortsListe.getListeVonOrten());
	}

	public static void update() {//Observable o, Object arg
		updateDisplayedList();

	}

	public static String getMapURL(String anschrift, int height) {
		String geocoordinate = null;
		try {
			if (anschrift != null) {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

				String encodedAnschrift = URLEncoder.encode(anschrift, "UTF-8").replace("+", " ");
				Document document = documentBuilder.parse(
						"https://nominatim.openstreetmap.org/search/" + encodedAnschrift + "?limit=1&format=xml");

				Node node = document.getElementsByTagName("place").item(0);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					geocoordinate = element.getAttribute("lat") + "," + element.getAttribute("lon");
				}
			}
		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (ParserConfigurationException e) {
		}

		String url = geocoordinate != null
				? "http://staticmap.openstreetmap.de/staticmap.php?center=" + geocoordinate + "&markers="
						+ geocoordinate + ",red-pushpin&zoom=16&size=300x" + height + "&maptype=mapnik"
				: "http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x" + height
						+ "&maptype=mapnik";

		return url;

	}

}
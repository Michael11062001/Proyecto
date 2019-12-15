package implementacion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import clases.Fondo;
import clases.Item;
import clases.JugadorAnimado;
import clases.Obstaculos;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Juego extends Application{
	private Scene escena;
	private Group root;
	private Canvas canvas;
	private GraphicsContext graficos;
	public static int puntuacion = 0;
	public static String nombreJugador=" ";
	private Fondo fondo;
	private Fondo fondo1;
	private Fondo fondo3;
	private Fondo fondo4;
	private JugadorAnimado jugadorAnimado;
	public static boolean derecha=true;
	public static boolean izquierda=false;
	public static boolean arriba=false;
	public static boolean abajo=false;
	public static boolean accion=false;
	public static HashMap<String, Image> imagenes;
	private ArrayList<Item> items;
	private ArrayList<Obstaculos> obstaculos;
	public static int vidas=1;
	public static int t=0;

	private ArrayList<Tile> tiles;

	private int[][] mapa = {
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,5,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,4,7,7},
			{0,0,0,0,0,5,7,7}
	};

	public static void main(String[] args) {
		do
			nombreJugador = JOptionPane.showInputDialog("Ingrese su nombre:");
		while(nombreJugador.equals(""));
		launch(args);
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarComponentes();
		graficos = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		ventana.setScene(escena);
		ventana.setTitle("The computer robot adventure");
		gestionarEventos();
		ventana.show();
		cicloJuego();
	}

	public void inicializarComponentes() {
		jugadorAnimado = new JugadorAnimado(50,260,"Personaje",3, "correr");
		root = new Group();
		escena = new Scene(root,1000,500);
		canvas  = new Canvas(1000,500);
		fondo= new Fondo(0,0,2,"Fondo");
		fondo1= new Fondo(1500,0,2,"Fondo1");
		fondo3= new Fondo(3000,0,2,"Fondo3");
		fondo4= new Fondo(4500,0,2,"Fondo4");
		imagenes = new HashMap<String,Image>();
		//item = new Item(400,285,0,0,"item");
		/*item2 = new Item(200,285,0,0,"item");
		item2 = new Item(600,285,0,0,"item");
		item3 = new Item(500,285,0,0,"item");
		item4 = new Item(700,285,0,0,"item");
		item5 = new Item(900,285,0,0,"item");*/
		items = new ArrayList<Item>();
		obstaculos = new ArrayList<Obstaculos>();
		cargarImagenes();
		cargarTiles();
		cargarItems();
	}

	private void cargarItems() {
		for(int i=0;i<mapa.length;i++) {
			obstaculos.add(new Obstaculos((i+1)*250, 320, 0, 0, "Obstaculo"));
			int rand1 = (int)(Math.random()*7)+1;
			items.add(new Item((i+1)*150, rand1*70, 0, 0, "item"));
		}
	}

	public void cargarImagenes() {
		imagenes.put("Fondo", new Image("country-platform-preview.png"));
		imagenes.put("Fondo1", new Image("country-platform-preview.png"));
		imagenes.put("Fondo3", new Image("country-platform-preview.png"));
		imagenes.put("Fondo4", new Image("country-platform-preview.png"));
		imagenes.put("tilemap", new Image("tilemap.png"));
		imagenes.put("item", new Image("manzana.png"));
		imagenes.put("Personaje", new Image("Sprites.png"));
		imagenes.put("Obstaculo", new Image("ObstaculoCopia.png"));

	}

	public void pintar() {
		graficos.setFill(Color.BLACK);
		graficos.fillRect(0, 0, 1000, 500);
		graficos.setFill(Color.WHITE);
		fondo1.pintar(graficos);
		fondo.pintar(graficos);
		fondo3.pintar(graficos);
		fondo4.pintar(graficos);
		for(int j=0;j<mapa.length;j++) {
			items.get(j).pintar(graficos);
			obstaculos.get(j).pintar(graficos);
		}
		for (int i=0;i<tiles.size();i++)
			tiles.get(i).pintar(graficos);
		graficos.fillText("Puntuacion: " + puntuacion, 10, 10);
		graficos.fillText("Jugador: " + nombreJugador, 10, 25);
		jugadorAnimado.pintar(graficos);
		/*item.pintar(graficos);
		item2.pintar(graficos);
		item3.pintar(graficos);
		item4.pintar(graficos);
		item5.pintar(graficos);*/
	}

	public void cargarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<mapa.length; i++) {
			for(int j=0; j<mapa[i].length; j++) {
				if (mapa[i][j]!=0)
					tiles.add(new Tile(mapa[i][j], i*70, j*70, "tilemap",0));
			}
		}
	}

	public void gestionarEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
					switch (evento.getCode().toString()) {
						case "UP":
							arriba=true;
							break;
						case "DOWN":
							abajo=true;
						case "SPACE":
							break;
						case "ESCAPE":
							try {
								BufferedWriter archivo = new BufferedWriter(new FileWriter("Jugadores.csv",true));
								archivo.write(Juego.puntuacion + "," + Juego.nombreJugador);
								archivo.flush();
								archivo.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.exit(0);
							break;
					}
			}
		});

		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				switch (evento.getCode().toString()) {
				case "UP":
					arriba=false;
					break;
				case "DOWN":
					abajo=false;
					break;
				case "SPACE":
					break;

	}
			}
		});
			}

	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				pintar();
				actualizar(t);

			}

		};
		animationTimer.start();
	}

	public void actualizar(double t) {
		jugadorAnimado.mover();
		jugadorAnimado.actualizarAnimacion(t);
		for(int i=0;i<mapa.length;i++) {
			jugadorAnimado.verificarColisiones(items.get(i));
			items.get(i).mover();
			jugadorAnimado.verificarColisiones(obstaculos.get(i));
			obstaculos.get(i).mover();
		}
		fondo1.mover();
		fondo.mover();
		fondo3.mover();
		fondo4.mover();
		//cambiarFondo();
	}
}
package clases;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado {
	private int x;
	private int y;
	private String indiceImagen;
	private int velocidad;
	private HashMap<String, Animacion> animaciones;
	private String animacionActual;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;



	public JugadorAnimado(int x, int y, String indiceImagen, int velocidad, String animacionActual) {
		super();
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;
		this.animacionActual = animacionActual;
		Juego.t=this.y;
		inicializarAnimaciones();
	}

	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getIndiceImagen() {
		return indiceImagen;
	}
	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}
	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
	}

	public void mover(){
		if (Juego.abajo)
			this.y+=velocidad;
		if (this.x>=1100)
			this.x = -100;
		if (Juego.arriba)
			this.y-=velocidad;
	}

	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(
				Juego.imagenes.get(this.indiceImagen),
				this.xImagen, this.yImagen,
				this.anchoImagen, this.altoImagen,
				this.x, this.y,
				this.anchoImagen, this.altoImagen
		);
	}

	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}

	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasCorrer[]= {
					new Rectangle(200,230, 72,91),
					new Rectangle(27,232, 73,91),
					new Rectangle(357,118, 87,88),
					new Rectangle(191,117, 81,92),
					new Rectangle(30,117, 83,91),
					new Rectangle(352,4, 100,93),
					new Rectangle(176,4, 110,91),
					new Rectangle(25, 4, 91,94)
			};


			Animacion animacionCorrer = new Animacion("correr",coordenadasCorrer,0.05);
			animaciones.put("correr",animacionCorrer);

			Rectangle coordenadasDescanso[] = {
					new Rectangle(26, 16, 63,73),
					new Rectangle(89,16, 63,73),
					new Rectangle(154,16, 63,73),
					new Rectangle(226,16, 63,73)
			};
			Animacion animacionDescanso = new Animacion("descanso",coordenadasDescanso,0.2);
			animaciones.put("descanso",animacionDescanso);
	}

	public void verificarColisiones(Item item) {
		if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
				if (!item.isCapturado())
					Juego.puntuacion++;
				item.setCapturado(true);
		}
	}

	public void verificarColisiones(Obstaculos obstaculos) {
		if (this.obtenerRectangulo().intersects(obstaculos.obtenerRectangulo().getBoundsInLocal())) {
				if (!obstaculos.isCapturado())
					Juego.vidas-=1;
				obstaculos.setCapturado(true);
				try {
					BufferedWriter archivo = new BufferedWriter(new FileWriter("Jugadores.csv",true));
					archivo.write(Juego.puntuacion + "," + Juego.nombreJugador + "\n");
					archivo.flush();
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(Juego.vidas==0)
					System.exit(0);
		}

	}
	/*public void verificarColisiones(Tile tiles) {
		if (this.obtenerRectangulo().intersects(tiles.obtenerRectangulo().getBoundsInLocal())) {
				if (tiles.isSobre())
					Juego.abajo=false;
		}*/
	}






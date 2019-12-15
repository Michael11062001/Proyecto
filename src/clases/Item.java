package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends Coordenadas{
	private int ancho;
	private int alto;
	private String indiceImagen;
	private boolean capturado;
	private int velocidad;


	public Item(int x, int y, int ancho, int alto, String indiceImagen) {
		super(x, y);
		this.ancho = ancho;
		this.alto = alto;
		this.indiceImagen = indiceImagen;
	}

	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(Juego.imagenes.get(indiceImagen), this.x, this.y);

	}

	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 18, 18);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	public void mover() {
		if (Juego.accion) {
			this.velocidad = 5;
		}else {
			this.velocidad = 1;
		}
		if (Juego.derecha)
			this.x-=velocidad;

		if (Juego.izquierda)
			this.x+=velocidad;
	}
}

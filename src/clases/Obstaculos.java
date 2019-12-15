
package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Obstaculos {
private int x;
private int y;
private int ancho;
private int alto;
private int velocidad;
private String indiceImagen;
private boolean capturado;
		public Obstaculos(int x, int y, int ancho, int alto, String indiceImagen) {
			super();
			this.x = x;
			this.y = y;
			this.ancho = ancho;
			this.alto = alto;
			this.indiceImagen = indiceImagen;
		}

		public void pintar(GraphicsContext graficos) {
				graficos.drawImage(Juego.imagenes.get(indiceImagen), this.x, this.y);
		}

		public Rectangle obtenerRectangulo() {
			return new Rectangle(this.x, this.y, 70, 60);
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


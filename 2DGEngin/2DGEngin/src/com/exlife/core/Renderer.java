package com.exlife.core;

import java.awt.image.DataBufferInt;

import com.exlife.core.gfx.Font;
import com.exlife.core.gfx.Image;

public class Renderer {

	private int width, height;
	private int[] pixels;
	private Font font = Font.STANDARD;

	public Renderer(GameContainer gc) {

		width = gc.getWidth();

		height = gc.getHeight();

		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}

	public void setPixel(int x, int y, int color) {

		if (x < 0 || x >= width || y < 0 || y >= height || color == 0xffff00ff) {

			return;

		}

		pixels[x + y * width] = color;

	}

	public void clear() {

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				setPixel(x, y, 0xff000000);

			}

		}

	}

	public void renderSting(String text, int color, int offX, int offY) {

		text = text.toUpperCase();

		int offset = 0;

		int unicode;

		for (int i = 0; i < text.length(); i++) {

			unicode = text.codePointAt(i) - 32;

			for (int x = 0; x < font.widths[unicode]; x++) {

				for (int y = 1; y < font.image.height; y++) {

					if (font.image.pixels[(x + font.offsets[unicode]) + y * font.image.width] == 0xffffffff) {

						setPixel(x + offX + offset, y + offY - 1, color);
					}

				}

			}

			offset += font.widths[unicode];

		}
	}

	public void renderImage(Image image, int offX, int offY) {

		for (int x = 0; x < image.width; x++) {

			for (int y = 0; y < image.height; y++) {

				setPixel(x + offX, y + offY, image.pixels[x + y * image.width]);

			}
		}

	}
}

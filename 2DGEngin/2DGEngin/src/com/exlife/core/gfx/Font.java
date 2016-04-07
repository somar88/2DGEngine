package com.exlife.core.gfx;

public enum Font {

	STANDARD("/fonts/standard.png");

	public final int NUM_UNICODE = 59;
	public int[] offsets = new int[NUM_UNICODE];
	public int[] widths = new int[NUM_UNICODE];
	public Image image;

	Font(String path) {

		image = new Image(path);

		int unicode = -1;

		int color;

		for (int x = 0; x < image.width; x++) {

			color = image.pixels[x];

			if (color == 0xff0000ff) {
				
				unicode++;
				
				offsets[unicode] = x;
			}

			if (color == 0xffffff00) {
				
				widths[unicode] = x - offsets[unicode];
			}
		}
	}

}

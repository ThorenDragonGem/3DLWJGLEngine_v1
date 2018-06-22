package graphics.datas;

public abstract class Datas
{
	public float[] DIMENSIONS;
	public float[] COLORS;
	public float[] TEXTCOORDS;
	public int[] INDICES;
	public float width, height;

	@Override
	public String toString()
	{
		String dimensions = "dimensions: ";
		for(int i = 0; i < DIMENSIONS.length; i++)
			dimensions += "[" + DIMENSIONS[i] + "]";
		String colors = " ; colors: ";
		for(int i = 0; i < COLORS.length; i++)
			colors += "[" + COLORS[i] + "]";
		String textCoords = " ; textCoords: ";
		for(int i = 0; i < TEXTCOORDS.length; i++)
			textCoords += "[" + TEXTCOORDS[i] + "]";
		String indices = " ; indices: ";
		for(int i = 0; i < INDICES.length; i++)
			indices += "[" + INDICES[i] + "]";
		return "[" + Float.toString(width) + "]" + "[" + Float.toString(height) + "]" + dimensions + colors + textCoords + indices;
	}
}

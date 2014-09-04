package Exercise01;
import javax.swing.JFrame;;

public class TestFrameViewer
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		final int FRAME_WIDTH = 250;
		final int FRAME_HEIGHT = 250;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("A Test Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

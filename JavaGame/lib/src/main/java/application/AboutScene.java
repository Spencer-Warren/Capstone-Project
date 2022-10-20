package application;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AboutScene extends SubScene {
	
	protected AboutScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
		initElements();
	}
	@Override
	protected void initElements() {
		createTitleBar("About");
		
		Label text = new Label("\tLorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nec tempus dui. Vivamus ipsum nisl, elementum at nibh ut, tempus rutrum erat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum nulla maximus, pretium mi in, ullamcorper mi. Suspendisse venenatis tincidunt lectus, et blandit ipsum bibendum vitae. Integer quis sem dui. Cras et ante arcu. Quisque blandit nunc eu hendrerit ultricies. Maecenas convallis maximus orci vel ornare. Curabitur eget lacus turpis. Aliquam id turpis eu ante sollicitudin tempus vitae ut nisl. Aenean metus lacus, iaculis nec libero ut, tempor fermentum lorem. Aenean dignissim nisl ex, sit amet finibus libero scelerisque nec.\n"
				+ "\n"
				+ "\tProin tincidunt massa rhoncus, suscipit justo sed, posuere magna. Sed nec libero leo. Aliquam eu augue tortor. Integer quis mauris vulputate, vulputate lorem ut, sollicitudin odio. Aliquam eget dignissim enim. Vivamus rutrum rhoncus fringilla. Sed eget vestibulum dui. Suspendisse non nisi vel purus maximus placerat ac ac libero. Morbi lobortis erat quis dignissim feugiat. Integer luctus magna in dignissim scelerisque. Duis dolor tortor, gravida at diam nec, rutrum pulvinar nisi. Integer interdum arcu id arcu cursus ornare. Etiam quis leo orci.\n"
				+ "\n"
				+ "\tQuisque diam odio, bibendum ornare elit ut, tincidunt consectetur diam. Morbi bibendum, lacus in pharetra mattis, magna augue fermentum odio, quis congue libero mi a dolor. Proin consectetur gravida hendrerit. Etiam bibendum nec velit eu luctus. Etiam congue venenatis massa, eget laoreet tortor semper nec. Mauris ornare neque a odio consectetur efficitur. Vestibulum sodales sit amet justo at rutrum. Nulla facilisi. Vivamus convallis consectetur massa, non dapibus risus vehicula at. Vivamus vulputate id magna eu placerat. Vivamus consequat semper iaculis. Nam convallis pellentesque libero, ut gravida risus ultrices id.\n"
				+ "\n"
				+ "\tPraesent in arcu vitae augue scelerisque luctus. Fusce placerat elementum ante in auctor. Sed condimentum iaculis arcu, at porta velit laoreet sit amet. Maecenas aliquet augue quis risus lacinia, nec viverra ipsum tempor. Mauris ut scelerisque felis. Curabitur non convallis nisi, in tincidunt sapien. Phasellus aliquam erat ut pellentesque tincidunt. Phasellus id nisl bibendum ante egestas faucibus quis in justo. Donec sed lectus libero. Nulla facilisi. Phasellus est nisl, commodo ut lobortis ut, tempor bibendum ante. Proin id commodo orci. Nullam semper ex in enim euismod, et auctor sapien pellentesque. Duis euismod, leo eu condimentum lacinia, libero dui pharetra urna, gravida vestibulum nisi lorem ut eros.\n"
				+ "\n"
				+ "");
		
		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setFont(new Font(15));
		
		getRoot().getChildren().add(text);

	}


}

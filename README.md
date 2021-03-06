# EaJEE : JEE made easy

## Temporary README

This repo will contain a set of specialized, easy to extend Servlets, filters, etc.

The goal is to make it extremely easy to write complex applications without the need for huge frameworks.

### Example

Here's an example of the `ActionServlet`, which maps urls of the form `/action/p1/p2/...` 
to calls for public methods of the form `doAction(String p1, String p2, ...)`

```java
import eajee.http.ActionResponse;
import eajee.http.ActionServlet;
import eajee.http.action.FileResponse;
import eajee.http.action.TextResponse;

public class PhotoServlet extends ActionServlet {
  private static final long serialVersionUID = -219881991336474131L;

	private String IMAGE_FOLDER = "...";

	private String getPictureTitle(String pictureId) {
		...
	}

	private String getPictureDescription(String pictureId) {
		...
	}

	public ActionResponse doAbout(String pictureId) {
		return new TextResponse(
				this.getPictureDescription(pictureId));
	}

	public ActionResponse doView(String pictureId) {
		return new FileResponse("image/jpeg", 
				IMAGE_FOLDER + "/" + pictureId + ".jpg",
				this.getPictureTitle(pictureId) + ".jpg");
	}
}
```

assuming this servlet is mapped to the path `/pictures` (on `localhost:8080/testApp`),
the URL `http://localhost:8080/testApp/pictures/view/1098756` would point to the image itself
(for download/embed), while `http://localhost:8080/testApp/pictures/about/1098756` would show 
a description/comment of the picture (served as text/plain)

### more

Another example is the `RestServlet`, used to easily create a REST web service, separating the 
object management aspect (storing, retrieving, etc) from the Input/Output handling, so that the
same actual resources can be presented in multiple ways (depending on the request).

Starting by extending the `RestServlet` abstract class will pretty much force you through
the right steps ... while I write a tutorial for it (soon).

Other elements will be added once I reach a stable enough API, 
current (UNSTABLE) code can be found on other branches (other than master).

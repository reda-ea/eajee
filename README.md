# EaJEE : JEE made easy

## Temporary README

This repo will contain a set of specialized, easy to extend Servlets, filters, etc.
The goal is to make it extremely easy to write complex applications without the need for huge frameworks.

### Example

```java
import eajee.http.ActionResponse;
import eajee.http.ActionServlet;
import eajee.http.action.FileResponse;
import eajee.http.action.TextResponse;

public class PhotoServlet extends ActionServlet {
  private static final long serialVersionUID = -219881991336474131L;

	String IMAGE_FOLDER;

	String getPictureTitle(String pictureId) {
		...
	}

	String getPictureDescription(String pictureId) {
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

Other elements will be added once I reach a stable enough API, 
current (UNSTABLE) code can be found on other branches (other than master).

package nextstep.jwp.controller;

import org.apache.coyote.controller.Controller;
import org.apache.coyote.http11.http.ContentType;
import org.apache.coyote.http11.http.HttpHeader;
import org.apache.coyote.http11.http.HttpRequest;
import org.apache.coyote.http11.http.HttpResponse;
import org.apache.coyote.http11.http.HttpStatus;
import org.apache.coyote.http11.util.StaticResourceUtil;

public class RegisterController implements Controller {

	private static final String REGISTER_HTML = "register.html";

	@Override
	public void service(HttpRequest request, HttpResponse response) throws Exception {
		response.setStatus(HttpStatus.OK);
		response.setBody(StaticResourceUtil.getContent(REGISTER_HTML));
		response.addHeader(HttpHeader.CONTENT_TYPE, ContentType.HTML.value());
	}
}

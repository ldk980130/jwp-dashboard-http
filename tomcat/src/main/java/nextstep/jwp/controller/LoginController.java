package nextstep.jwp.controller;

import static org.apache.coyote.http11.http.session.Session.*;

import java.util.Optional;

import org.apache.coyote.controller.AbstractController;
import org.apache.coyote.http11.http.HttpRequest;
import org.apache.coyote.http11.http.HttpResponse;
import org.apache.coyote.http11.http.HttpStatus;
import org.apache.coyote.http11.http.session.Session;

import nextstep.jwp.exception.InputEmptyException;
import nextstep.jwp.exception.InvalidLoginException;
import nextstep.jwp.model.User;
import nextstep.jwp.service.LoginService;

public class LoginController extends AbstractController {

	private static final String LOGIN_HTML = "login.html";
	private static final String UNAUTHORIZED_HTML = "401.html";
	private static final String BAD_REQUEST_HTML = "400.html";
	private static final String REDIRECT_URL = "/index.html";
	private static final String LOGIN_USER = "user";

	@Override
	protected void doGet(HttpRequest request, HttpResponse response) {
		Session session = request.getSession();
		Optional<Object> sessionAttribute = session.getAttribute(LOGIN_USER);

		sessionAttribute.ifPresentOrElse(
			user -> response.sendRedirect(REDIRECT_URL),
			() -> handleHtml(HttpStatus.OK, LOGIN_HTML, response)
		);
	}

	@Override
	protected void doPost(HttpRequest request, HttpResponse response) {
		String account = request.getQueryString("account");
		String password = request.getQueryString("password");

		try {
			User user = LoginService.login(account, password);
			response.sendRedirect(REDIRECT_URL);
			addLoginSession(request, response, user);
		} catch (InvalidLoginException e) {
			handleHtml(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_HTML, response);
		} catch (InputEmptyException e) {
			handleHtml(HttpStatus.BAD_REQUEST, BAD_REQUEST_HTML, response);
		}
	}

	private void addLoginSession(HttpRequest request, HttpResponse response, User user) {
		Session session = request.getSession();
		session.setAttribute(LOGIN_USER, user);
		response.addCookie(JSESSIONID, session.getId());
	}
}

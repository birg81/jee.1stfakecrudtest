package controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import model.PersonDAO;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/api/response.json"})
public class ApiOperations extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Person p = new Person(0, "Biagio Rosario", "Greco", "m", 40, 171);
		/*
		 * try { p = new Gson().fromJson(
		 * req.getReader().lines().collect(Collectors.joining()), Person.class);
		 * } catch (Exception e) { }
		 */
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		res.getWriter().printf("""
				{
					"message": "aggiunte %d persone"
				}
				""", PersonDAO.addPerson(p));
	}
	// other alternative post implementation
	protected void doMyOderPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().printf(
			"%s",
			req.getReader()
				.lines()
				.collect(
					Collectors.joining()
				).toLowerCase()
		);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		res.getWriter().printf("""
				{
					"head": {
						"loacalAddr": "%s",
						"remoteAddr": "%s",
						"method": "%s"
					},
					"people": %s
				}
				""", req.getLocalAddr(), req.getRemoteAddr(), req.getMethod(),
				new Gson().toJson(PersonDAO.getPeople()));
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		res.getWriter().printf("""
				{
					"message": "modificate %d persone"
				}
				""", PersonDAO.modifyPerson(new Person(PersonDAO.findLast(),
				"Del Gaudio", "Anna", "f", 32, 165)));
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		res.getWriter().printf("""
				{
					"message": "eliminate %d persone"
				}
				""", PersonDAO.rmPerson(PersonDAO.findLast()));

	}
}

package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonDAO {
	private static final String URLstr = "jdbc:mysql://localhost:3306/PersonDB?user=root&password=";
	// Add Person
	public static int addPerson(Person p) {
		int rows = 0;
		final Connection con;
		final String q = """
				INSERT INTO	PersonList(lastname, firstname, gender, age, h) VALUES
				(?, ?, ?, ?, ?);
				"""
				.trim();
		try {
			con = DriverManager.getConnection(URLstr);
			final PreparedStatement pst = con.prepareStatement(q);
			pst.setString(1, p.getLastname());
			pst.setString(2, p.getFirstname());
			pst.setString(3, p.getGender());
			pst.setInt(4, p.getAge());
			pst.setInt(5, p.getH());
			rows = pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	// List of Person
	public static ArrayList<Person> getPeople() {
		ArrayList<Person> plist = new ArrayList<>();
		final Connection con;
		final String q = """
				SELECT *
				FROM PersonList
				ORDER BY
					lastname ASC,
					firstname ASC,
					age DESC;
				""".trim();
		try {
			con = DriverManager.getConnection(URLstr);
			final ResultSet rs = con.createStatement().executeQuery(q);
			for (; rs.next();)
				plist.add(new Person(rs.getInt("id"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("gender"),
						rs.getInt("age"), rs.getInt("h")));
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist;
	}
	// Update Person
	public static int modifyPerson(Person p) {
		int rows = 0;
		final Connection con;
		final String q = """
				UPDATE PersonList
				SET
					lastname=?,
					firstname=?,
					gender=?,
					age=?,
					h=?
				WHERE id=?;
				""".trim();
		try {
			con = DriverManager.getConnection(URLstr);
			final PreparedStatement pst = con.prepareStatement(q);
			pst.setString(1, p.getLastname());
			pst.setString(2, p.getFirstname());
			pst.setString(3, p.getGender());
			pst.setInt(4, p.getAge());
			pst.setInt(5, p.getH());
			pst.setInt(6, p.getId());
			rows = pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	// Remove Person
	public static int rmPerson(int id) {
		int rows = 0;
		final Connection con;
		final String q = "DELETE FROM PersonList WHERE id=?;";
		if (id > 0 && id <= findLast())
			try {
				con = DriverManager.getConnection(URLstr);
				final PreparedStatement pst = con.prepareStatement(q);
				pst.setInt(1, id);
				rows = pst.executeUpdate();
				pst.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return rows;
	}
	// find last insert People
	public static int findLast() {
		int id = 0;
		final Connection con;
		final String q = "SELECT MAX(id) AS lastIndex FROM PersonList;";
		try {
			con = DriverManager.getConnection(URLstr);
			ResultSet rs = con.createStatement().executeQuery(q);
			while (rs.next())
				id = rs.getInt("lastIndex");
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
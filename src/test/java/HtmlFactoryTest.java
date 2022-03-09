package netflix;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.epsi.netflix.HtmlFactory;
import fr.epsi.netflix.Movie;
import fr.epsi.netflix.MovieFactory;

/**
 * Test pour HtmlFactory
 *
 */
public class HtmlFactoryTest
{
	/**
	 * Test la génération de détails
	 */
	@Test
	public void testGenerateDetail()
	{
		String line = "s1|Movie|Dick Johnson Is Dead|Kirsten Johnson||United States|September 25 2021|2020|PG-13|90 min|Documentaries|Blablabla.";
		String[] fake = line.split("\\|");

		Movie movie = MovieFactory.getInstance().createMovie(fake);

		HtmlFactory factory = new HtmlFactory();
		String result = factory.generateDetail(movie);

		assertEquals(
				"<!doctype html><head> <meta charset=\"utf-8\"><title>Dick Johnson Is Dead</title></head><body><div><p>Titre</p><p>Dick Johnson Is Dead</p></div><div><p>Desccription</p><p>Blablabla.</p></div></body></html>",
				result);
	}

	/**
	 * Test la génération du html
	 */
	@Test
	public void testGenerateHtml()
	{
		String line = "s1|Movie|Dick Johnson Is Dead|Kirsten Johnson||United States|September 25 2021|2020|PG-13|90 min|Documentaries|Blablabla.";
		String[] fake = line.split("\\|");
		Movie movie = MovieFactory.getInstance().createMovie(fake);

		List<Movie> datas = new ArrayList<Movie>();
		datas.add(movie);

		HtmlFactory factory = new HtmlFactory();
		String result = factory.generateHtml(datas);

		assertEquals(
				"<!doctype html><head> <meta charset=\"utf-8\"><title>Films/Series</title></head><body><style> table, table tr, table td, table th {border:1px solid black;border-collapse:collapse}</style><table><tr><th>id</th><th>type</th><th>titre</th><th>RÃ©alisateur</th><th>Pays</th><th>Date</th><th>Detail</th></tr><tr><td>s1</td><td>Movie</td><td>Dick Johnson Is Dead</td><td>Kirsten Johnson</td><td>United States</td><td>2021-09-25</td><td><a href='movies/s1.html'>detail</td></tr></table></body></html>",
				result);

	}
}

package netflix;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import fr.epsi.netflix.Movie;
import fr.epsi.netflix.MovieFactory;

/**
 * Test pour MovieFactory
 *
 */
public class MovieFactoryTest
{
	/**
	 * Formateur pour rÃ©aliser la convertion des dates.
	 */
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US);

	/**
	 * Test la création d'un film à partir d'un tableau
	 */
	@Test
	public void testCreateMovie()
	{
		String line = "s1|Movie|Dick Johnson Is Dead|Kirsten Johnson||United States|September 25 2021|2020|PG-13|90 min|Documentaries|Blablabla.";
		String[] fake = line.split("\\|");

		Movie result = MovieFactory.getInstance().createMovie(fake);

		assertEquals("s1", result.getId());
		assertEquals("Movie", result.getType());
		assertEquals("Dick Johnson Is Dead", result.getTitle());
		assertEquals("Kirsten Johnson", result.getDirector());
		assertEquals("", result.getCast());
		assertEquals("United States", result.getCountry());
		assertEquals(formatter.parse("September 25 2021".trim(), LocalDate::from), result.getDateAdded());
		assertEquals(2020, result.getReleaseYear());
		assertEquals("PG-13", result.getRating());
		assertEquals("90 min", result.getDuration());
		assertEquals("Documentaries", result.getListedIn());
		assertEquals("Blablabla.", result.getDescription());
	}

	/**
	 * Test la création de film par liste
	 */
	@Test
	public void testCreateMovies()
	{
		String line1 = "s1|Movie|Dick Johnson Is Dead|Kirsten Johnson||United States|September 25 2021|2020|PG-13|90 min|Documentaries|Blablabla.";
		String line2 = "s1|Movie|Dick Johnson Is Dead|Kirsten Johnson||United States|September 25 2021|2020|PG-13|90 min|Documentaries|Blablabla.";

		List<String> datas = new ArrayList<String>();
		datas.add(line1);
		datas.add(line2);

		List<Movie> results = MovieFactory.getInstance().createMovies(datas);

		for (Movie result : results)
		{
			assertEquals("s1", result.getId());
			assertEquals("Movie", result.getType());
			assertEquals("Dick Johnson Is Dead", result.getTitle());
			assertEquals("Kirsten Johnson", result.getDirector());
			assertEquals("", result.getCast());
			assertEquals("United States", result.getCountry());
			assertEquals(formatter.parse("September 25 2021".trim(), LocalDate::from), result.getDateAdded());
			assertEquals(2020, result.getReleaseYear());
			assertEquals("PG-13", result.getRating());
			assertEquals("90 min", result.getDuration());
			assertEquals("Documentaries", result.getListedIn());
			assertEquals("Blablabla.", result.getDescription());
		}
	}
}

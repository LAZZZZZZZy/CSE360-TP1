/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class Path {
	private String name;
	private double duration;
	/**
	 * 
	 */
	public Path (String _name, double _duration) {
		name = _name;
		duration = _duration;
	}
	/**
	 * @return the name
	 */
	public String getName () {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName ( String name ) {
		this.name = name;
	}
	/**
	 * @return the duration
	 */
	public double getDuration () {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration ( double duration ) {
		this.duration = duration;
	}

}

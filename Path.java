/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class Path {
	private String name;
	private int duration;
	/**
	 * 
	 */
	public Path (String _name, int _duration) {
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
	public void setDuration ( int duration ) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return name+"   "+duration;
		
	}
}

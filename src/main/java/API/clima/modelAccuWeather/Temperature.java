package API.clima.modelAccuWeather;

public class Temperature {

	private Metric Metric;

    private Imperial Imperial;

    public Metric getMetric ()
    {
        return Metric;
    }

    public void setMetric (Metric Metric)
    {
        this.Metric = Metric;
    }

    public Imperial getImperial ()
    {
        return Imperial;
    }

    public void setImperial (Imperial Imperial)
    {
        this.Imperial = Imperial;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Metric = "+Metric+", Imperial = "+Imperial+"]";
    }
	
}

package com.journaldev.spring.discovery;

public class Discovery {
	private Results[] results;

    private String matching_results;

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    public String getMatching_results ()
    {
        return matching_results;
    }

    public void setMatching_results (String matching_results)
    {
        this.matching_results = matching_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", matching_results = "+matching_results+"]";
    }
}

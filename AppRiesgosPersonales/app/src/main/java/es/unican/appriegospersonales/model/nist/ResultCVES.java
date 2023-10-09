package es.unican.appriegospersonales.model.nist;

import java.util.List;

public class ResultCVES {

    private int totalResults;

    private List<Vulnerability> vulnerabilities;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    @Override
    public String toString() {
        return "ResultCVES{" +
                "totalResults=" + totalResults +
                ", vulnerabilities=" + vulnerabilities +
                '}';
    }
}

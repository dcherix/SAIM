package de.uni_leipzig.simba.saim.gui.widget.form;

import java.util.HashMap;

/**
 * Bean to automatically build form for genetic learner configuration.
 * @author Lyko
 *
 */
public class LearnerConfigurationBean {
	
	public LearnerConfigurationBean() {
		setDefaultValues();
	}
	
	/**
	 * To set some default values.
	 */
	private void setDefaultValues() {
		setGenerations(20);
		setPopulation(20);
		setMutationRate(0.4d);
		setNumberOfInqueriesPerRun(10);
	}

	public int getGenerations() {
		return generations;
	}
	public void setGenerations(int generations) {
		this.generations = generations;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public double getMutationRate() {
		return mutationRate;
	}
	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}
	public int getNumberOfInqueriesPerRun() {
		return numberOfInqueriesPerRun;
	}
	public void setNumberOfInqueriesPerRun(int numberOfInqueriesPerRun) {
			this.numberOfInqueriesPerRun = numberOfInqueriesPerRun;
	}
	private int generations;
	private int population;
	private double mutationRate;
	private int numberOfInqueriesPerRun;
	@Override
	public String toString() {
		return "BEAN: gens="+generations+" pop="+population+" mutationRate="+mutationRate+" inquieries="+numberOfInqueriesPerRun;
	}

	/**
	 * Generate params HashMap used by leraner.
	 * @return
	 */
	public HashMap<String, Object> createParams() {
		HashMap<String, Object> params = new HashMap<String, Object>();
	    params.put("populationSize", getPopulation());
		params.put("generations", getGenerations());
		params.put("mutationRate", (float)getMutationRate());
		params.put("trainingDataSize", getNumberOfInqueriesPerRun());
		return params;
	}
}
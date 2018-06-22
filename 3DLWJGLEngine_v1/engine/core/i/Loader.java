package core.i;

public interface Loader
{
	boolean isFinished();

	boolean isExecuted();

	void executeLoader();

	void cleanUp() throws Throwable;
}

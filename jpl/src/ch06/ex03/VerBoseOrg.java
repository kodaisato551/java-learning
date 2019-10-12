package ch06.ex03;

public interface VerBoseOrg {
	int SILENT = 0;
	int TERSE = 1;
	int NORMAL = 2;
	int VEROBOSE = 3;

	void setVerbosity(int level);

	int getVerbosity();

}

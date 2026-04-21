package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxTry = 2; // retry twice

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxTry) {
            count++;
            return true;
        }
        return false;
    }
}

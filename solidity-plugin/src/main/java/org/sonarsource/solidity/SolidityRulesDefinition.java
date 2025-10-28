package org.sonarsource.solidity;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.solidity.externalreport.AbstractExternalReportSensor;
import org.sonarsource.solidity.externalreport.SoliumReportSensor;

public class SolidityRulesDefinition implements RulesDefinition {

    protected static final String KEY = "solidity";
    protected static final String NAME = "Solidity";
    public static final String REPO_KEY = KEY + "-" + KEY;
    protected static final String REPO_NAME = NAME + " Rules";

    private final boolean externalIssuesSupported;

    public SolidityRulesDefinition(boolean reportExternalIssues) {
        this.externalIssuesSupported = reportExternalIssues;
    }

    @Override
    public void define(Context context) {
        NewRepository repository = context
                .createRepository(REPO_KEY, KEY)
                .setName(REPO_NAME);

        // Load rules từ resource JSON (Sonar tự động load)
        repository.done();

        if (externalIssuesSupported) {
            AbstractExternalReportSensor.createExternalRuleRepository(
                    context,
                    SoliumReportSensor.LINTER_ID,
                    SoliumReportSensor.LINTER_NAME
            );
        }
    }
}

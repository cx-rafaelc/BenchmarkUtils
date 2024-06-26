/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details
 *
 * @author Raj Barath
 * @created 2023
 */
package org.owasp.benchmarkutils.score.parsers;

import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SnykReader extends SarifReader {

    @Override
    protected String expectedSarifToolName() {
        return "SnykCode";
    }

    @Override
    protected boolean isCommercial() {
        return true;
    }

    @Override
    protected Map<String, Integer> ruleCweMappings(JSONArray rules) {
        Map<String, Integer> mappings = new HashMap<>();

        for (int i = 0; i < rules.length(); i++) {
            JSONObject rule = rules.getJSONObject(i);

            int cwe =
                    parseInt(
                            rule.getJSONObject("properties")
                                    .getJSONArray("cwe")
                                    .getString(0)
                                    .substring(4));

            mappings.put(rule.getString("id"), cwe);
        }

        return mappings;
    }
}

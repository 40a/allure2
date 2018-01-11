package io.qameta.allure.duration;

import io.qameta.allure.CommonJsonAggregator;
import io.qameta.allure.core.LaunchResults;
import io.qameta.allure.entity.TestResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Plugin that generates data for Duration graph.
 *
 * @since 2.0
 */
public class DurationPlugin extends CommonJsonAggregator {

    public DurationPlugin() {
        super("widgets", "duration.json");
    }

    @Override
    protected List<DurationData> getData(final List<LaunchResults> launchesResults) {
        return launchesResults.stream()
                .flatMap(launch -> launch.getResults().stream())
                .map(this::createData)
                .collect(Collectors.toList());
    }

    private DurationData createData(final TestResult result) {
        return new DurationData()
                .setUid(result.getId())
                .setName(result.getName())
                .setStatus(result.getStatus())
                .setStart(result.getStart())
                .setStop(result.getStop())
                .setDuration(result.getDuration())
                .setSeverity(result.getExtraBlock("severity"));
    }
}

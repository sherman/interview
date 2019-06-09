package org.sherman.interview.misc;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis M. Gabaydulin
 * @since 09.06.19
 */
public class FindMaxClients {
    private static final Logger log = LoggerFactory.getLogger(FindMaxClients.class);

    @Test(dataProvider = "cases")
    public void findMaxClients(List<ClientTime> clientTimeData, int expected) {
        assertEquals(findMaxClients(clientTimeData), expected);
    }

    private int findMaxClients(List<ClientTime> clientTimeData) {
        List<Action> actions = clientTimeData.stream().map(
            elt ->
                ImmutableList.of(
                    new Action(elt.arriveTime, ActionType.ARRIVE),
                    new Action(elt.leaveTime, ActionType.LEAVE)
                )
        )
            .flatMap(List::stream)
            .collect(Collectors.toList());

        Collections.sort(actions);

        int maxClients = 0;
        int current = 0;

        for (Action action : actions) {
            if (action.type == ActionType.ARRIVE) {
                current++;
                maxClients = Math.max(maxClients, current);
            } else {
                current--;
            }
        }

        log.info("{}" + actions);

        return maxClients;
    }

    @DataProvider
    private static Object[][] cases() {
        return new Object[][]{
            {
                ImmutableList.of(
                    new ClientTime(1, 6),
                    new ClientTime(3, 8),
                    new ClientTime(4, 5),
                    new ClientTime(6, 10)
                ),
                3
            },

            {
                ImmutableList.of(
                    new ClientTime(1, 6),
                    new ClientTime(6, 7),
                    new ClientTime(7, 8)
                ),
                1
            }
        };
    }

    private static class ClientTime {
        final int arriveTime;
        final int leaveTime;

        private ClientTime(int arriveTime, int leaveTime) {
            this.arriveTime = arriveTime;
            this.leaveTime = leaveTime;
        }
    }

    private enum ActionType {
        ARRIVE,
        LEAVE
    }

    private static class Action implements Comparable<Action> {
        final int time;
        final ActionType type;

        private Action(int time, ActionType type) {
            this.time = time;
            this.type = type;
        }

        public int getTime() {
            return time;
        }

        public ActionType getType() {
            return type;
        }

        @Override
        public int compareTo(@NotNull Action o) {
            return Comparator.comparingInt(Action::getTime)
                .compare(this, o);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("time", time)
                .add("type", type)
                .toString();
        }
    }
}

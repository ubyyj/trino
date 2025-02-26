/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.spi.connector;

import java.util.Objects;
import java.util.StringJoiner;

import static io.trino.spi.connector.MaterializedViewFreshness.Freshness.FRESH;
import static io.trino.spi.connector.MaterializedViewFreshness.Freshness.STALE;
import static java.util.Objects.requireNonNull;

public final class MaterializedViewFreshness
{
    private final Freshness freshness;

    @Deprecated
    public MaterializedViewFreshness(boolean materializedViewFresh)
    {
        this(materializedViewFresh ? FRESH : STALE);
    }

    public MaterializedViewFreshness(Freshness freshness)
    {
        this.freshness = requireNonNull(freshness, "freshness is null");
    }

    public Freshness getFreshness()
    {
        return freshness;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        MaterializedViewFreshness that = (MaterializedViewFreshness) obj;
        return freshness == that.freshness;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(freshness);
    }

    @Override
    public String toString()
    {
        return new StringJoiner(", ", MaterializedViewFreshness.class.getSimpleName() + "[", "]")
                .add("freshness=" + freshness)
                .toString();
    }

    public enum Freshness
    {
        FRESH,
        STALE,
        UNKNOWN,
        /**/
    }
}

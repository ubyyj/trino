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
package io.trino.plugin.deltalake;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.multibindings.MapBinder;
import io.trino.plugin.deltalake.transactionlog.writer.S3TransactionLogSynchronizer;
import io.trino.plugin.deltalake.transactionlog.writer.TransactionLogSynchronizer;

import static com.google.inject.multibindings.MapBinder.newMapBinder;
import static io.airlift.json.JsonCodecBinder.jsonCodecBinder;

public class DeltaLakeS3Module
        implements Module
{
    @Override
    public void configure(Binder binder)
    {
        MapBinder<String, TransactionLogSynchronizer> logSynchronizerMapBinder = newMapBinder(binder, String.class, TransactionLogSynchronizer.class);
        jsonCodecBinder(binder).bindJsonCodec(S3TransactionLogSynchronizer.LockFileContents.class);
        logSynchronizerMapBinder.addBinding("s3").to(S3TransactionLogSynchronizer.class).in(Scopes.SINGLETON);
        logSynchronizerMapBinder.addBinding("s3a").to(S3TransactionLogSynchronizer.class).in(Scopes.SINGLETON);
        logSynchronizerMapBinder.addBinding("s3n").to(S3TransactionLogSynchronizer.class).in(Scopes.SINGLETON);
    }
}

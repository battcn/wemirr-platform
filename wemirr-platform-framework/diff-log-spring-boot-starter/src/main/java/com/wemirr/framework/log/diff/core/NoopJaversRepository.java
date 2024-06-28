package com.wemirr.framework.log.diff.core;

import org.javers.core.commit.Commit;
import org.javers.core.commit.CommitId;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.type.EntityType;
import org.javers.core.metamodel.type.ManagedType;
import org.javers.repository.api.JaversRepository;
import org.javers.repository.api.QueryParams;
import org.javers.repository.api.SnapshotIdentifier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Levin
 */
public class NoopJaversRepository implements JaversRepository {

    public static NoopJaversRepository INSTANCE = new NoopJaversRepository();

    @Override
    public List<CdoSnapshot> getStateHistory(GlobalId globalId, QueryParams queryParams) {
        return List.of();
    }

    @Override
    public List<CdoSnapshot> getValueObjectStateHistory(EntityType ownerEntity, String path, QueryParams queryParams) {
        return List.of();
    }

    @Override
    public List<CdoSnapshot> getStateHistory(Set<ManagedType> givenClasses, QueryParams queryParams) {
        return List.of();
    }

    @Override
    public Optional<CdoSnapshot> getLatest(GlobalId globalId) {
        return Optional.empty();
    }

    @Override
    public List<CdoSnapshot> getSnapshots(QueryParams queryParams) {
        return List.of();
    }

    @Override
    public List<CdoSnapshot> getSnapshots(Collection<SnapshotIdentifier> snapshotIdentifiers) {
        return List.of();
    }

    @Override
    public void persist(Commit commit) {

    }

    @Override
    public CommitId getHeadId() {
        return null;
    }

    @Override
    public void setJsonConverter(JsonConverter jsonConverter) {

    }

    @Override
    public void ensureSchema() {

    }
}

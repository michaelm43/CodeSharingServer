package smartspace.dao;

import java.util.List;
import java.util.Optional;

import smartspace.data.ElementEntity;

public interface EnhancedElementDao<Key> extends ElementDao<Key> {
	public List<ElementEntity> readAll(int size, int page);
	public List<ElementEntity> readAll(String sortBy, int size, int page);
	public List<ElementEntity> readMessageWithCreatorContaining (String creator, int size, int page);
	public ElementEntity createImportAction(ElementEntity entity);
	public List<ElementEntity> readAllUsingName(String name, int size, int page);
	public ElementEntity createWithId(ElementEntity elementEntity);
	public void updateLinesOfCode(ElementEntity element);
}
package refdiff.core.diff;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import refdiff.core.cst.CstNode;
import refdiff.core.cst.CstRoot;

/**
 * A CstDiff represents the relationships between nodes of two CST's.
 * Relationships are usually refactorings applied between revisions.
 * 
 * @see RelationshipType
 */
public class CstDiff {
	
	private final CstRoot before;
	private final CstRoot after;
	private final Set<Relationship> relationships = new HashSet<>();
	private Set<CstNode> changedEntities;
	
	public CstDiff(CstRoot before, CstRoot after) {
		this.before = before;
		this.after = after;
	}
	
	/**
	 * @return The CST of the revision before the change.
	 */
	public CstRoot getBefore() {
		return before;
	}
	
	/**
	 * @return The CST of the revision after the change.
	 */
	public CstRoot getAfter() {
		return after;
	}
	
	/**
	 * @return The set of relationships between nodes of the CST's (before and after). Relationships are usually 
	 * refactorings applied between revisions.
	 */
	public Set<Relationship> getRelationships() {
		return Collections.unmodifiableSet(relationships);
	}
	
	/**
	 * Add a relationship in the CST diff.
	 * 
	 * @param relationship The relationship to add.
	 */
	public void addRelationships(Relationship relationship) {
		relationships.add(relationship);
	}
	
	public void setChangedEntities(Set<CstNode> changed){
		this.changedEntities = changed;
	}
	
	public Set<CstNode> getChangedEntities(){
		return this.changedEntities;
	}

	public Set<CstNode> getChangedEntities(String type){
		return this.changedEntities.stream().filter(c -> c.getType().equals(type))
		.collect(Collectors.toSet());
	}

	/**
	 * @return The set of refactoring relationships between nodes of the CST's (before and after).
	 */
	public Set<Relationship> getRefactoringRelationships() {
		return relationships.stream()
			.filter(Relationship::isRefactoring)
			.collect(Collectors.toSet());
	}
}

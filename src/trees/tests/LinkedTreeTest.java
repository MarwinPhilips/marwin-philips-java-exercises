package trees.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import trees.classes.LinkedTree;
import trees.interfaces.Position;
import trees.interfaces.Tree;

public class LinkedTreeTest {

	@Test
	public void test1() {
		Tree<Integer> tree = new LinkedTree<Integer>();

		// empty tree
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(0, tree.elements().size());
		assertEquals(0, tree.positions().size());
		assertEquals("", tree.toString());
		

		// 1
		Position<Integer> root = tree.addRoot(1);
		assertFalse(tree.isInternal(root));
		assertTrue(tree.isExternal(root));
		assertFalse(tree.isEmpty());
		assertEquals(1, tree.size());
		assertEquals(0, tree.height());
		assertEquals(1, tree.elements().size());
		assertEquals(1, tree.positions().size());
		assertEquals(root, tree.root());
		assertEquals((Integer) 1, root.element());
		assertEquals(0, tree.depth(root));
		assertEquals(0, tree.ancestors(root).size());
		assertEquals(0, tree.descendants(root).size());
		assertEquals("1", tree.toString());

		// 1 (2)
		Position<Integer> child1 = tree.insertChild(root, 2);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child1));
		assertTrue(tree.isExternal(child1));
		assertFalse(tree.isEmpty());
		assertEquals(2, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child1, tree.children(tree.root()).get(0));
		assertEquals((Integer) 2, child1.element());
		assertEquals(2, tree.elements().size());
		assertEquals(2, tree.positions().size());
		assertEquals(1, tree.depth(child1));
		assertEquals(1, tree.ancestors(child1).size());
		assertEquals(0, tree.descendants(child1).size());
		assertEquals(1, tree.descendants(root).size());
		assertEquals("1 (2)", tree.toString());

		// 1 (2 3)
		Position<Integer> child2 = tree.insertChild(root, 3);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child2));
		assertTrue(tree.isExternal(child2));
		assertFalse(tree.isEmpty());
		assertEquals(3, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child2, tree.children(tree.root()).get(1));
		assertEquals((Integer) 3, child2.element());
		assertEquals(3, tree.elements().size());
		assertEquals(3, tree.positions().size());
		assertEquals(1, tree.depth(child2));
		assertEquals(1, tree.ancestors(child2).size());
		assertEquals(0, tree.descendants(child2).size());
		assertEquals(2, tree.descendants(root).size());
		assertEquals("1 (2 3)", tree.toString());

		// 1 (2 3 4)
		Position<Integer> child3 = tree.insertChild(root, 4);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child3));
		assertTrue(tree.isExternal(child3));
		assertFalse(tree.isEmpty());
		assertEquals(4, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child3, tree.children(tree.root()).get(2));
		assertEquals((Integer) 4, child3.element());
		assertEquals(4, tree.elements().size());
		assertEquals(4, tree.positions().size());
		assertEquals(1, tree.depth(child3));
		assertEquals(1, tree.ancestors(child3).size());
		assertEquals(0, tree.descendants(child3).size());
		assertEquals(3, tree.descendants(root).size());
		assertEquals("1 (2 3 4)", tree.toString());
		
		// 1 (2 3 (5) 4)
		Position<Integer> child21 = tree.insertChild(child2, 5);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child21));
		assertTrue(tree.isExternal(child21));
		assertFalse(tree.isEmpty());
		assertEquals(5, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child21, tree.children(child2).get(0));
		assertEquals((Integer) 5, child21.element());
		assertEquals(5, tree.elements().size());
		assertEquals(5, tree.positions().size());
		assertEquals(2, tree.depth(child21));
		assertEquals(2, tree.ancestors(child21).size());
		assertEquals(0, tree.descendants(child21).size());
		assertEquals(4, tree.descendants(root).size());
		assertEquals("1 (2 3 (5) 4)", tree.toString());

		// 1 (2 3 (5 6) 4)
		Position<Integer> child22 = tree.insertChild(child2, 6);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child22));
		assertTrue(tree.isExternal(child22));
		assertFalse(tree.isEmpty());
		assertEquals(6, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child22, tree.children(child2).get(1));
		assertEquals((Integer) 6, child22.element());
		assertEquals(6, tree.elements().size());
		assertEquals(6, tree.positions().size());
		assertEquals(2, tree.depth(child22));
		assertEquals(2, tree.ancestors(child22).size());
		assertEquals(0, tree.descendants(child22).size());
		assertEquals(5, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 6) 4)", tree.toString());

		// 1 (2 3 (5 6) 4 (7))
		Position<Integer> child31 = tree.insertChild(child3, 7);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child31));
		assertTrue(tree.isExternal(child31));
		assertFalse(tree.isEmpty());
		assertEquals(7, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child31, tree.children(child3).get(0));
		assertEquals((Integer) 7, child31.element());
		assertEquals(7, tree.elements().size());
		assertEquals(7, tree.positions().size());
		assertEquals(2, tree.depth(child31));
		assertEquals(2, tree.ancestors(child31).size());
		assertEquals(0, tree.descendants(child31).size());
		assertEquals(6, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 6) 4 (7))", tree.toString());

		// 1 (2 3 (5 6 (8)) 4 (7))
		Position<Integer> child221 = tree.insertChild(child22, 8);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child221));
		assertTrue(tree.isExternal(child221));
		assertFalse(tree.isEmpty());
		assertEquals(8, tree.size());
		assertEquals(3, tree.height());
		assertEquals(child221, tree.children(child22).get(0));
		assertEquals((Integer) 8, child221.element());
		assertEquals(8, tree.elements().size());
		assertEquals(8, tree.positions().size());
		assertEquals(3, tree.depth(child221));
		assertEquals(3, tree.ancestors(child221).size());
		assertEquals(0, tree.descendants(child221).size());
		assertEquals(7, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 6 (8)) 4 (7))", tree.toString());
		
		tree.replaceElement(child2, 9);
		assertEquals("1 (2 9 (5 6 (8)) 4 (7))", tree.toString());

		tree.swapElements(child21, child22);
		assertEquals("1 (2 9 (6 5 (8)) 4 (7))", tree.toString());
	}

	@Test
	public void test2() {
		Tree<Integer> tree = new LinkedTree<>();
		Position<Integer> p = tree.addRoot(0);
		for (int i=1; i<100; i++) {
			p = tree.insertChild(p, i);
			assertFalse(tree.isEmpty());
			assertEquals(i+1, tree.size());
			assertEquals(i, tree.height());
			assertEquals(i+1, tree.elements().size());
			assertEquals(i+1, tree.positions().size());
			assertEquals(i, tree.depth(p));
			assertEquals(i, tree.ancestors(p).size());
			assertEquals(0, tree.descendants(p).size());
			assertEquals(i, tree.descendants(tree.root()).size());			
		}

	}

}

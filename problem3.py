# This problem was asked by Google.

# Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), 
# which deserializes the string back into the tree.

# For example, given the following Node class

# class Node:
#     def __init__(self, val, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
# The following test should pass:

# node = Node('root', Node('left', Node('left.left')), Node('right'))
# assert deserialize(serialize(node)).left.left.val == 'left.left'




EMPTY_NODE = "#None#"

class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def serialize(root):
    if root is None:
        return EMPTY_NODE
    else:
        val = root.val

    val = '%s,%s' % (val, serialize(root.left))
    val = '%s,%s' % (val, serialize(root.right))
    return val


def deserialize(string):
    if string is None:
         return None

    tokens = iter( string.split(",") )
    return create_node(tokens)


def create_node(tokens):
    val = next(tokens, None)

    if val is None or val == EMPTY_NODE:
        return None
    else:
        return Node(val=val,
                    left=create_node(tokens),
                    right=create_node(tokens))


#TESTS

node = Node('root', Node('left', Node('left.left')), Node('right'))
deserialized = deserialize(serialize(node))

assert deserialized.val == 'root'
assert deserialized.left.val == 'left'
assert deserialized.left.left.val == 'left.left'
assert deserialized.right.val == 'right'
assert deserialized.left.left.left == None
assert deserialized.left.right == None
assert deserialized.left.left.right == None
assert deserialized.right.left == None

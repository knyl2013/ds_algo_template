void Morris(struct TreeNode* root) { 
    TreeNode* prev = NULL;

    if (root == NULL) 
        return; 

    TreeNode* curr = root; 

    while (curr != NULL) { 

        if (curr->left == NULL) { 
            cout << curr->val << endl; 
            curr = curr->right; 
        } 

        else { 
            /* Find the previous (prev) of curr */
            prev = curr->left; 
            while (prev->right != NULL && prev->right != curr) 
                prev = prev->right; 

            /* Make curr as the right child of its
            previous */
            if (prev->right == NULL) { 
                prev->right = curr; 
                curr = curr->left; 
            } 

            /* fix the right child of previous */
            else { 
                prev->right = NULL; 
                cout << curr->val << endl; 
                curr = curr->right; 
            } 
        } 
    } 
} 


struct TreeNode* add_node(int data) { 
    struct TreeNode* node = new TreeNode(); 
    node->val = data; 
    node->left = NULL; 
    node->right = NULL; 
    return (node); 
} 
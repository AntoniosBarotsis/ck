package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class DIT extends ASTVisitor implements ClassLevelMetric {

	int dit = 1; // Object is the father of everyone!

	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		if(binding!=null) calculate(binding);

		return super.visit(node);
	}

	private void calculate(ITypeBinding binding) {
		ITypeBinding father = binding.getSuperclass();
		if (father != null) {
			String fatherName = father.getQualifiedName();
			if (fatherName.endsWith("Object")) return;
			dit++;

			calculate(father);
		}

	}

	@Override
	public void setResult(CKClassResult result) {
		result.setDit(dit);
	}

}

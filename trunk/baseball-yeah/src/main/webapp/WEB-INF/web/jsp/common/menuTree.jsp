<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript">
	$(function() {
		var setting = {
			check : {
				enable : true,
				chkStyle :"checkbox"
			},
			data : {
				key : {
					title : "title"
				},
				simpleData : {
					enable : true
				}
			},
			callback : {
				onCheck : onCheck
			}
		};
		$.get("/manage/menu/menuZtree", function(data, status) {
			$(document).ready(function() {
				$.fn.zTree.init($("#treeDemo"), setting, data);
				$("#hideNodesBtn").bind("click", {
					type : "rename"
				}, hideNodes);
				$("#showNodesBtn").bind("click", {
					type : "icon"
				}, showNodes);
				setTitle();
				count();
			});
		});

		function onCheck(e, treeId, treeNode) {
			count();
		}

		function setTitle(node) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = node ? [ node ] : zTree.transformToArray(zTree
					.getNodes());
			for (var i = 0, l = nodes.length; i < l; i++) {
				var n = nodes[i];
				n.title = "[" + n.id + "] isFirstNode = " + n.isFirstNode
						+ ", isLastNode = " + n.isLastNode;
				zTree.updateNode(n);
			}
		}
		function count() {
			function isForceHidden(node) {
				if (!node.parentTId)
					return false;
				var p = node.getParentNode();
				return !!p.isHidden ? true : isForceHidden(p);
			}
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"), checkCount = zTree
					.getCheckedNodes(true).length, nocheckCount = zTree
					.getCheckedNodes(false).length, hiddenNodes = zTree
					.getNodesByParam("isHidden", true), hiddenCount = hiddenNodes.length;

			for (var i = 0, j = hiddenNodes.length; i < j; i++) {
				var n = hiddenNodes[i];
				if (isForceHidden(n)) {
					hiddenCount -= 1;
				} else if (n.isParent) {
					hiddenCount += zTree.transformToArray(n.children).length;
				}
			}

		}
		function showNodes() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
					.getNodesByParam("isHidden", true);
			zTree.showNodes(nodes);
			setTitle();
			count();
		}
		function hideNodes() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
					.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请至少选择一个节点");
				return;
			}
			zTree.hideNodes(nodes);
			setTitle();
			count();
		}
	});
</script>

<ul id="treeDemo" class="ztree"></ul>
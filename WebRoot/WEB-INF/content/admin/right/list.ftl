

<table class="table table-hover">
	<thead>
		<tr>
			<th><@s.text name="table.id"></@s.text></th>
			<th><@s.text name="table.name"></@s.text></th>
			<th><@s.text name="table.url"></@s.text></th>
			<th><@s.text name="table.isMenu"></@s.text></th>
			<th><@s.text name="table.isPublic"></@s.text></th>
			<th><@s.text name="table.pos"></@s.text></th>
			<th><@s.text name="table.code"></@s.text></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<@s.iterator value="allRight">
			<td><@s.checkbox fieldValue="%{id}" value="false" name="id" /> <@s.property value="id"/></td>
			<td><@s.a action="update" target="_blank">
				<@s.param name="id">${id }</@s.param>
				<@s.property value="name"/>
				</@s.a>
			</td>
			<td><@s.property value="url"/></td>
			<td><@s.property value="isMenu"/></td>
			<td><@s.property value="isPublic"/></td>
			<td><@s.property value="pos"/></td>
			<td><@s.property value="code"/></td>
		</tr>
		</@s.iterator>
		
	</tbody>
</table>


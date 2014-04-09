<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:include value="../public/header.jsp"></s:include>

<s:form action="user-save" namespace="/admin" method="post"
	id="surveyAdd" cssClass="SFrom">
	<ul>
		<li><label for="username" class="contro-label"> <s:text
					name="username" />
		</label>
			<div class="controls">
				<s:textfield name="name" id="username" />
				<span class="help-inline" title=""><s:fielderror name="name" /></span>
			</div></li>

		<li><label for="nickName" class="contro-label"> <s:text
					name="nickName" />
		</label>
			<div class="controls">
				<s:textfield name="nickName" id="nickName" />
				<span class="help-inline" title=""><s:fielderror
						name="nickName" /></span>
			</div></li>

		<li><label for="username" class="contro-label"> <s:text
					name="password" />
		</label>
			<div class="controls">
				<s:textfield name="password" id="password" />
				<span class="help-inline" title=""><s:fielderror
						name="password" /></span>
			</div></li>

		<li><label for="username" class="contro-label"> <s:text
					name="confirmPassword" />
		</label>
			<div class="controls">
				<s:textfield name="confirmPassword" id="confirmPassword" />
				<span class="help-inline" title=""><s:fielderror name="name" /></span>
			</div></li>

		<li><label for="email" class="contro-label"> <s:text
					name="email" />
		</label>
			<div class="controls">
				<s:textfield name="email" id="email" />
				<span class="help-inline" title=""><s:fielderror name="email" /></span>
			</div></li>

		<li><s:submit name="sava" key="save" cssClass="subs" /></li>

	</ul>


	<fieldset>
		<legend>Legend</legend>
		<label><s:text name="username" /></label>
		<div data-role="input-control" class="input-control text">
			<s:textfield name="name" id="username" placeholder="type text" />
			<span class="help-inline" title=""><s:fielderror name="name" /></span>
			<button tabindex="-1" class="btn-clear" type="button"></button>
		</div>
		<label>Label name</label>
		<div data-role="input-control" class="input-control password">
			<input type="password" autofocus="" placeholder="type password">
			<button tabindex="-1" class="btn-reveal" type="button"></button>
		</div>
		<div data-role="input-control" class="input-control text">
			<input type="text">
			<button class="btn-search"></button>
		</div>
		<div data-role="input-control"
			class="input-control text warning-state">
			<input type="text" value="warning state">
		</div>
		<div data-role="input-control" class="input-control file">
			<input type="file" style="z-index: 0;" tabindex="-1"><input
				type="text" style="z-index: 1; cursor: default;" readonly=""
				id="__input_file_wrapper__">
			<button class="btn-file" type="button"></button>
		</div>

		<div data-role="input-control" class="input-control text error-state">
			<input type="text" value="error state">
		</div>
		<div data-role="input-control" class="input-control text info-state">
			<input type="text" value="info state">
		</div>
		<div data-role="input-control"
			class="input-control text success-state">
			<input type="text" value="info state">
		</div>

		<div data-role="input-control" class="input-control checkbox">
			<label class="inline-block"> <input type="checkbox">
				<span class="check"></span> Check me out
			</label> <label class="inline-block"> <input type="checkbox"
				checked=""> <span class="check"></span> Check me out
			</label> <label class="inline-block"> Check me out <input
				type="checkbox" disabled=""> <span class="check"></span>
			</label> <label class="inline-block"> Check me out <input
				type="checkbox" checked="" disabled=""> <span class="check"></span>
			</label> <label class="inline-block"> Intermediate <input
				type="checkbox" data-show="intermediate"> <span
				class="check"></span>
			</label>
		</div>


		<div class="clearfix">
			<div data-role="input-control"
				class="input-control radio inline-block">
				<label class="inline-block"> <input type="radio" checked=""
					name="r1"> <span class="check"></span> R1
				</label> <label class="inline-block"> <input type="radio" name="r1">
					<span class="check"></span> R1
				</label>
			</div>
			<div data-role="input-control"
				class="input-control radio default-style inline-block">
				<label class="inline-block"> <input type="radio" checked=""
					name="r2"> <span class="check"></span> R1
				</label> <label class="inline-block"> <input type="radio" name="r2">
					<span class="check"></span> R2
				</label>
			</div>

			<div>
				<div data-role="input-control" class="input-control switch">
					<label style="margin-right: 20px" class="inline-block">
						Switch me <input type="checkbox" checked=""> <span
						class="check"></span>
					</label> <label class="inline-block"> <input type="checkbox"
						disabled=""> <span class="check"></span> Switch disabled
					</label>
				</div>
			</div>
		</div>
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset"> <input type="button" value="Button">

		<div style="margin-top: 20px"></div>

	</fieldset>
</s:form>


<s:include value="../public/footer.jsp"></s:include>
package survey;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

public class JFreechar {

	public void textPie() throws IOException {
		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		dpd.setValue("管理人员", 25); // 输入数据
		dpd.setValue("市场人员", 25);
		dpd.setValue("开发人员", 45);
		dpd.setValue("其他人员", 10);

		JFreeChart chart = ChartFactory.createPieChart("某公司人员组织数据图", dpd, true,
				false, false);
		// 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
		Font font = new Font("宋体", Font.ITALIC, 30);
		// 标题字体
		chart.getTitle().setFont(font);
		// 提示条字体
		chart.getLegend().setItemFont(font);

		PiePlot plost = (PiePlot) chart.getPlot();
		plost.setLabelFont(font);
		// 绘制背景图片
		// plost.setBackgroundImage(ImageIO.read(new File("")));

		// chart.setBackgroundImage(ImageIO.read(new File("")));
		//
		// 提高分离效果
		plost.setExplodePercent("其他人员", 0.1f);

		ChartUtilities
				.saveChartAsJPEG(new File("./pie-1.jpg"), chart, 800, 600);

	}

	public void textPie3D() throws IOException {
		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		dpd.setValue("管理人员", 250); // 输入数据
		dpd.setValue("市场人员", 250);
		dpd.setValue("开发人员", 450);
		dpd.setValue("其他人员", 100);

		JFreeChart chart = ChartFactory.createPieChart3D("某公司人员组织数据图", dpd,
				true, false, false);
		// 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
		Font font = new Font("宋体", Font.ITALIC, 24);
		// 标题字体
		chart.getTitle().setFont(font);
		// 提示条字体
		chart.getLegend().setItemFont(font);

		PiePlot plost = (PiePlot) chart.getPlot();
		plost.setLabelFont(font);
		// 绘制背景图片
		// plost.setBackgroundImage(ImageIO.read(new File("")));

		// chart.setBackgroundImage(ImageIO.read(new File("")));
		//
		// 设置分离效果
		plost.setExplodePercent("其他人员", 0.1f);
		// 设置前景透明
		plost.setForegroundAlpha(0.85f);
		// 设置标签
		// {0}标签名称
		// {1}结果数指
		// {2}百分比
		// {3}综合
		plost.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}/{1}/{2}/{3}"));

		ChartUtilities.saveChartAsJPEG(new File("./pie3d-1.jpg"), chart, 800,
				600);

	}

	@Test
	public void testBar() throws IOException {

		// 创建类别数据集
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.setValue(2500, "IBM", "第一季度");
		ds.setValue(3600, "Oracle", "第一季度");
		ds.setValue(2820, "拥有", "第一季度");

		ds.setValue(2500, "IBM", "第二季度");
		ds.setValue(3600, "Oracle", "第二季度");
		ds.setValue(2820, "拥有", "第二季度");

		ds.setValue(2500, "IBM", "第三季度");
		ds.setValue(3600, "Oracle", "第三季度");
		ds.setValue(2820, "拥有", "第三季度");

		//
		String t1 = "前三季度销售统计";
		String t2 = "季度";
		String t3 = "销量(单位:万台)";

		Font font = new Font("宋体", Font.ITALIC, 30);
		JFreeChart freechar = ChartFactory.createBarChart(t1, t2, t3, ds,
				PlotOrientation.VERTICAL, true, false, false);
		// 标题字体
		freechar.getTitle().setFont(font);

		// 得到绘图区域
		CategoryPlot plot = (CategoryPlot) freechar.getPlot();
		// domain轴标签字体
		plot.getDomainAxis().setLabelFont(font);
		// 小标签
		plot.getDomainAxis().setTickLabelFont(font);

		// range轴标签字体
		plot.getRangeAxis().setLabelFont(font);
		plot.getRangeAxis().setTickLabelFont(font);

		ChartUtilities.saveChartAsJPEG(new File("./bar-1.jpg"), freechar, 800,
				600);

	}
	
	@Test
	public void testLine() throws IOException {

		// 创建类别数据集
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.setValue(2500, "IBM", "第一季度");
		ds.setValue(3600, "Oracle", "第一季度");
		ds.setValue(2820, "拥有", "第一季度");

		ds.setValue(2500, "IBM", "第二季度");
		ds.setValue(3600, "Oracle", "第二季度");
		ds.setValue(2820, "拥有", "第二季度");

		ds.setValue(2500, "IBM", "第三季度");
		ds.setValue(3600, "Oracle", "第三季度");
		ds.setValue(2820, "拥有", "第三季度");

		//
		String t1 = "前三季度销售统计";
		String t2 = "季度";
		String t3 = "销量(单位:万台)";

		Font font = new Font("宋体", Font.ITALIC, 30);
		JFreeChart freechar = ChartFactory.createLineChart(t1, t2, t3, ds,
				PlotOrientation.HORIZONTAL, true, false, false);
		// 标题字体
		freechar.getTitle().setFont(font);

		// 得到绘图区域
		CategoryPlot plot = (CategoryPlot) freechar.getPlot();
		// domain轴标签字体
		plot.getDomainAxis().setLabelFont(font);
		// 小标签
		plot.getDomainAxis().setTickLabelFont(font);

		// range轴标签字体
		plot.getRangeAxis().setLabelFont(font);
		plot.getRangeAxis().setTickLabelFont(font);

		ChartUtilities.saveChartAsJPEG(new File("./line-1.jpg"), freechar, 800,
				600);
		
		

	}
}

using Microsoft.AspNetCore.Mvc;
using StudentManagerMVC.Models;
namespace StudentManagerMVC.Controllers;

public class HomeController : Controller
{
    private readonly HttpClient _client;

    private readonly ILogger<HomeController> _logger;

    private readonly IConfiguration _configuration;

    public HomeController(ILogger<HomeController> logger, IConfiguration configuration)
    {
        this._logger = logger;
        this._client = new HttpClient();
        this._configuration = configuration;
    }

    [HttpGet]
    public async Task<IActionResult> Index()
    {
        var getRequest = await this._client.GetAsync(this._configuration.GetSection("APIServerUrl").Value);
        getRequest.EnsureSuccessStatusCode();
        StudentModel[]? students = await getRequest.Content.ReadFromJsonAsync<StudentModel[]>();
        return View(students);
    }

    [HttpDelete]
    public async Task<IActionResult> Delete(int? id)
    {
        var getRequest = await this._client.DeleteAsync($"{this._configuration.GetSection("APIServerUrl").Value}/{id}");
        getRequest.EnsureSuccessStatusCode();
        return this.Redirect("/Home");
    }

    public IActionResult Create() => View();
    public async Task<IActionResult> Update(int? id)
    {
        var getRequest = await this._client.GetAsync($"{this._configuration.GetSection("APIServerUrl").Value}/{id}");
        getRequest.EnsureSuccessStatusCode();
        StudentModel? student = await getRequest.Content.ReadFromJsonAsync<StudentModel>();
        return View(student);
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Update([Bind("id,name,email,stream")] StudentModel updateData)
    {
        if (ModelState.IsValid)
        {
            var getRequest = await this._client.PutAsJsonAsync(this._configuration.GetSection("APIServerUrl").Value, updateData);
            getRequest.EnsureSuccessStatusCode();
        }
        return this.Redirect("/Home");
    }


    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create([Bind("name,email,stream")] CreateStudentModel createData)
    {
        if (ModelState.IsValid)
        {
            var getRequest = await this._client.PostAsJsonAsync(this._configuration.GetSection("APIServerUrl").Value, createData);
            getRequest.EnsureSuccessStatusCode();
        }
        return this.Redirect("/Home");
    }

    [HttpGet]
    public async Task<IActionResult> Detail(int? id)
    {
        var getRequest = await this._client.GetAsync($"{this._configuration.GetSection("APIServerUrl").Value}/{id}");
        getRequest.EnsureSuccessStatusCode();
        StudentModel? student = await getRequest.Content.ReadFromJsonAsync<StudentModel>();
        return View(student);
    }

}
